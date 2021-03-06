/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import model.User;
import model.Wall;
import org.primefaces.model.LazyDataModel;
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
    @ManagedProperty(value = "#{userController.target}")
    private User target;
    private EntityManager em;
    private LazyDataModel<Wall> lazyModel;
    private List<Wall> walls;

    /**
     * Creates a new instance of WallController
     */
    public WallController() {
        user = new User();
        target = new User();
        em = EMF.createEntityManager();
        lazyModel = new LazyWallDataModel(walls);
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public List<Wall> getWalls() {
        walls = this.retrieveWall();
        return walls;
    }
    
    public List<Wall> getOtherWalls() {
        walls = (List<Wall>) em.createNamedQuery("Wall.findByUser").setParameter("user", target).getResultList();
        Collections.reverse(walls);
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
