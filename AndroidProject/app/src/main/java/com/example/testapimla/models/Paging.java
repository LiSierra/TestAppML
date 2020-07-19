package com.example.testapimla.models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto para la paginación del resultado de la busqueda
 */
public class Paging {
    /**
     * Mapea el total de la busqueda
     */
    @SerializedName("total")
    private int totalItems;

    /**
     *  Obtiene el total de la busqueda
     * @return
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Asigna el total de la busqueda
     * @param totalItems
     */
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
