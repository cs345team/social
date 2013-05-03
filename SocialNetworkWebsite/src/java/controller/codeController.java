/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.InviteCode;
import model.User;
import server.EMF;
import server.UUIDGenerater;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class codeController {

    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private String newCode;
    /**
     * Creates a new instance of codeController
     */
    public codeController() {
        em = EMF.createEntityManager();
        newCode = UUIDGenerater.generateUniqueId();
    }
    
    @PostConstruct
    public void init() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        InviteCode c = new InviteCode();
        em.persist(c);
        c.setOwner(user);
        c.setCode(newCode);
        tx.commit();
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
