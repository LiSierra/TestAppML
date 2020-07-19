package com.example.testapimla.presenter.search;

import com.example.testapimla.models.Search;

import java.util.List;

/**
 * Interfaz donde se definen los métodos a implementar en el presentador
 * para la consulta del producto.
 */
public interface IGetSearchPresenter {
    void getListSites();
    void getSearchItems(String idSite, String keyWord,
                        String offset, String limit);
    void fillListSiteSP();
    void fillSearchMoviesRV(List<Search> items);
    String getIdSite(String country);
    void validateSearchItem(String country, String keyWord,
                            boolean isNewPage);
}

