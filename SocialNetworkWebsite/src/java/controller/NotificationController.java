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
import model.Requests;
import model.User;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class NotificationController {

    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private List<User> requesters = new ArrayList<User>();

    /**
     * Creates a new instance of NotificationController
     */
    public NotificationController() {
        em = EMF.createEntityManager();
    }

    public List<User> getRequesters() {
        List<Requests> requestList = (List<Requests>) em.createNamedQuery("Requests.findByRequestee").setParameter("requestee", user).getResultList();
        if (!requestList.isEmpty()) {
            for (Requests r : requestList) {
                requesters.add(r.getRequester());
            }
        }
        return requesters;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
