package com.gillsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.gillsoft.abstract_rest_service.AbstractLocalityService;
import com.gillsoft.cache.IOCacheException;
import com.gillsoft.client.RestClient;
import com.gillsoft.model.Locality;
import com.gillsoft.model.request.LocalityRequest;
import com.google.common.collect.Lists;

@RestController
public class LocalityServiceController extends AbstractLocalityService {
	
	private static List<Locality> all;
	private static Map<String, List<String>> binding;
	
	@Autowired
	private RestClient client;

	@Override
	public List<Locality> getAllResponse(LocalityRequest request) {
		return getAllLocalities(request);
	}

	@Override
	public Map<String, List<String>> getBindingResponse(LocalityRequest request) {
		createLocalities();
		return binding;
	}

	@Override
	public List<Locality> getUsedResponse(LocalityRequest request) {
		return getAllLocalities(request);
	}
	
	private List<Locality> getAllLocalities(LocalityRequest request) {
		createLocalities();
		if (all != null) {
			return Lists.newArrayList(all);
		}
		return new ArrayList<>();
	}
	
	@Scheduled(initialDelay = 60000, fixedDelay = 900000)
	public void createLocalities() {
		if (LocalityServiceController.all == null) {
			synchronized (LocalityServiceController.class) {
				if (LocalityServiceController.all == null) {
					boolean cacheError = true;
					do {
						try {
							all = client.getCachedStations();
							if (all != null) {
								/*List<String> localityIdList = stations.stream().map(Locality::getId)
										.collect(Collectors.toList());
								binding = new ConcurrentHashMap<>();
								localityIdList.forEach(id -> binding.put(id, localityIdList.stream()
										.filter(f -> !f.equals(id)).collect(Collectors.toList())));*/
								cacheError = false;
							}
						} catch (IOCacheException e) {
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException ie) {
							}
						}
					} while (cacheError);
				}
			}
		}
	}
	
	public static Locality getLocality(String id) {
		if (all == null) {
			return null;
		}
		for (Locality locality : all) {
			if (Objects.equals(id, locality.getId()) || locality.getName().containsValue(id)) {
				return locality;
			}
		}
		return null;
	}

}
