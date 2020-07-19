package com.example.testapimla.preferences;

import android.content.Context;

/**
 * Interfaz que define la inicialización del cache
 */
public interface IFactoryCache {
    ICache createCache(Context context);
}
