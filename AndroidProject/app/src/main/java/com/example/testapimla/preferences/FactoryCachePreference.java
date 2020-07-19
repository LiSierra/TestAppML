package com.example.testapimla.preferences;

import android.content.Context;

/**
 * Clase que implementa la fabrica para el cache
 */
public class FactoryCachePreference implements IFactoryCache{
    private ICache cache;

    @Override
    public ICache createCache(Context context) {
        if(cache == null) {
            cache = new CachePreferences(context);
        }

        return cache;
    }
}
