/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import model.Friends;
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
    private List<User> friends;
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
    
    public List<User> getFriends() {
        friends = new ArrayList<User>();
        List<Friends> list = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", friend).getResultList();
        if (!list.isEmpty()) {
            for (Friends f : list) {
                friends.add(f.getFriend());
            }
        }
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
