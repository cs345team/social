/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Image;
import model.User;
import model.Wall;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import server.EMF;
import server.LazyWallDataModel;

/**
 *
 * @author jichao
 */
@ManagedBean
@RequestScoped
public class WallController {

    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private LazyDataModel<Wall> lazyModel;
    private List<Wall> walls;

    /**
     * Creates a new instance of WallController
     */
    public WallController() {
        user = new User();
        em = EMF.createEntityManager();
        lazyModel = new LazyWallDataModel(walls);
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Wall> getWalls() {
        walls = this.retrieveWall();
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }
    
    private List<Wall> retrieveWall()
    {
        List<Wall> wallList = (List<Wall>) em.createNamedQuery("Wall.findByUser").setParameter("user", user).getResultList();
        Collections.reverse(wallList);
        return wallList;
    }
}
