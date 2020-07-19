package com.example.testapimla.models;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto para la información del producto
 */
public class Search {
    /**
     * Mapea del id del producto
     */
    @SerializedName("id")
    private String idItem;
    /**
     * Mapea el nombre del producto
     */
    @SerializedName("title")
    private String titleItem;
    /**
     * Mapea el precio del producto
     */
    @SerializedName("price")
    private String priceItem;
    /**
     * Mapea la imagen del producto
     */
    @SerializedName("thumbnail")
    private String imageItem;

    /**
     * Obtiene el id del producto
     * @return
     */
    public String getIdItem() {
        return idItem;
    }

    /**
     * Asigna el id del producto
     * @param idItem
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * Obtiene del nombre del producto
     * @return
     */
    public String getTitleItem() {
        return titleItem;
    }

    /**
     * Asigna del nombre del producto
     * @param titleItem
     */
    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    /**
     * Obtiene el precio del producto
     * @return
     */
    public String getPriceItem() {
        return priceItem;
    }

    /**
     * Asigna el precio al producto
     * @param priceItem
     */
    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem;
    }

    /**
     * Obtiene la imagen del producto
     * @return
     */
    public String getImageItem() {
        return imageItem;
    }

    /**
     * Asigna la imagen del producto
     * @param imageItem
     */
    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

}
