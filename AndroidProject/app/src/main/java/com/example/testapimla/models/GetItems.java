package com.example.testapimla.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Objeto para el array obtenido de la busqueda
 */
public class GetItems {
    /**
     * Mapea el objeto de paginación
     */
    @SerializedName("paging")
    private Paging paging;
    /**
     * Mapea el array de resultados de la busqueda
     */
    @SerializedName("results")
    private List<Search> getItems = null;

    /**
     * Obtiene la lista de resultados de un producto
     * @return
     */
    public List<Search> getGetItems() {
        return getItems;
    }

    /**
     * Agina valores a la lista de resultados de un producto
     * @param getItems
     */
    public void setGetItems(List<Search> getItems) {
        this.getItems = getItems;
    }

    /**
     * Obtiene los datos de paginación
     * @return
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * Asigna datos de paginación
     * @param paging
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
