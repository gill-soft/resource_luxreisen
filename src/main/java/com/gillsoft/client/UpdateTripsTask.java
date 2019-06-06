package com.gillsoft.client;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gillsoft.cache.IOCacheException;
import com.gillsoft.cache.RedisMemoryCache;
import com.gillsoft.model.request.TripSearchRequest;
import com.gillsoft.util.ContextProvider;

public class UpdateTripsTask implements Runnable, Serializable {
	
	private static final long serialVersionUID = -612450869121241871L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateTripsTask.class);
	
	private String key;

	private TripSearchRequest request;
	
	public UpdateTripsTask() {

	}

	public UpdateTripsTask(String key) {
		this.key = key;
	}

	public UpdateTripsTask(TripSearchRequest request) {
		this.request = request;
		this.key = getKey(request);
	}

	public static String getKey(TripSearchRequest request) {
		return String.join("|",
				(request.getParams() != null && request.getParams().getHost() != null) ? request.getParams().getHost()
						: "",
				Arrays.deepToString(request.getLocalityPairs().toArray()), request.getDates().toString(),
				request.getCurrency() == null ? "" : request.getCurrency().toString());
	}

	@Override
	public void run() {
		Map<String, Object> params = new HashMap<>();
		params.put(RedisMemoryCache.OBJECT_NAME, key);
		params.put(RedisMemoryCache.UPDATE_TASK, this);
		params.put(RedisMemoryCache.UPDATE_DELAY, Config.getCacheTripUpdateDelay());
		TripPackage tripPackage = null;
		
		// получаем рейсы для создания кэша
		RestClient client = ContextProvider.getBean(RestClient.class);
		long[] timeToLive = { 0L };
		try {
			tripPackage = client.getTrips(request);
			timeToLive[0] = getTimeToLive(tripPackage);
			params.put(RedisMemoryCache.TIME_TO_LIVE, timeToLive[0]);
		} catch (ResponseError e) {
			// ошибку поиска тоже кладем в кэш но с другим временем жизни
			params.put(RedisMemoryCache.TIME_TO_LIVE, Config.getCacheErrorTimeToLive());
			params.put(RedisMemoryCache.UPDATE_DELAY, Config.getCacheErrorUpdateDelay());
			tripPackage = new TripPackage();
			tripPackage.setError(e);
		}
		try {
			client.getCache().write(tripPackage, params);
			tripPackage.getRouteList().forEach(route -> {
				Map<String, Object> routeParams = new HashMap<>();
				routeParams.put(RedisMemoryCache.OBJECT_NAME, route.getRouteId());
				routeParams.put(RedisMemoryCache.TIME_TO_LIVE, timeToLive[0]);
				try {
					client.getCache().write(key, routeParams);
				} catch (Exception e) {
					
				}
			});
		} catch (IOCacheException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	// время жизни до момента самого позднего отправления
	private long getTimeToLive(TripPackage tripPackage) {
		if (Config.getCacheTripTimeToLive() != 0) {
			return Config.getCacheTripTimeToLive();
		}
		long max = 0;
		if (tripPackage.getRouteList() != null && !tripPackage.getRouteList().isEmpty()) {
			for (com.gillsoft.client.model.info.route.Route route : tripPackage.getRouteList()) {
				if (route.getDatetimeArrival() != null && route.getDatetimeArrival().getTime() > max) {
					max = route.getDatetimeArrival().getTime();
				}
			}
			return max - System.currentTimeMillis();
		}
		return 0;
	}

}
