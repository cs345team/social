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
public class FriendHomeController {
    
    @ManagedProperty(value = "#{userController.target}")
    private User friend;
    private EntityManager em;
    private LazyDataModel<Wall> lazyModel;
    private List<Wall> walls;
    /**
     * Creates a new instance of FriendHomeController
     */
    public FriendHomeController() {
        friend = new User();
        em = EMF.createEntityManager();
        lazyModel = new LazyWallDataModel(walls);
    }
    
    public User getFriend() {
        return friend;
    }

    public void setFriend(User user) {
        this.friend = user;
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
        List<Wall> wallList = (List<Wall>) em.createNamedQuery("Wall.findByUser").setParameter("user", friend).getResultList();
        Collections.reverse(wallList);
        return wallList;
    }
}
