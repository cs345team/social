/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@RequestScoped
public class SearchController {

    private String keyword;

    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
    }

    public String doSearch() {
        return "/user/searchResult.xhtml?faces-redirect=true&keyword=" + keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
