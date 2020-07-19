package com.example.testapimla.preferences;

import android.content.Context;

/**
 * Clase que administra la inicialización y creación de cache
 */
public class CacheFactory {
    private ICache cache;
    private IFactoryCache factoryCache;
    private static CacheFactory instance;

    public static CacheFactory getInstance() {
        if (instance == null) {
            instance = new CacheFactory();
        }
        return instance;
    }

    public void setCurrent(IFactoryCache cacheFactory){
        this.factoryCache = cacheFactory;
    }

    public ICache createCache(Context context){
        if(factoryCache == null){
            System.out.println("Fabric cache null");
        }

        if(cache == null){
            cache = factoryCache.createCache(context);
        }
        return  cache;
    }

    private CacheFactory(){

    }
}
