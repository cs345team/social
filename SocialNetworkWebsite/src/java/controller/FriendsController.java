/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import model.Friends;
import model.User;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class FriendsController {

    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private List<User> friends = new ArrayList<User>();

    /**
     * Creates a new instance of resultController
     */
    public FriendsController() {
        em = EMF.createEntityManager();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFriends() {
        List<Friends> list = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", user).getResultList();
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
