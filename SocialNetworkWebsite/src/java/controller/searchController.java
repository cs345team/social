/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Xi Yang
 */
@ManagedBean
@RequestScoped
public class searchController {

    private String keyword;
     
    /**
     * Creates a new instance of searchController
     */
    public searchController() {
        
    }
}
