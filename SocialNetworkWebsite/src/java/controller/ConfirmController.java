/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.User;
import server.EMF;

/**
 *
 * @author jichao
 */
@ManagedBean
@RequestScoped
public class ConfirmController {

    private EntityManager em;
    private User user;
    @ManagedProperty(value = "#{param.confirmationCode}")
    private String confirmationCode;
    private String message;

    /**
     * Creates a new instance of ConfirmController
     */
    public ConfirmController() {
        em = EMF.createEntityManager();
        confirmationCode = "";
    }

    public String confirm() {
        List<User> list = (List<User>) em.createNamedQuery("User.findByConfirmationCode").setParameter("confirmationCode", confirmationCode).getResultList();

        if (!list.isEmpty()) {
            user = list.get(0);
            changeUserConfirmStatus(user);
        } else {
            message = "Activition failed.";
        }
        return message;
    }

    public void changeUserConfirmStatus(User user) {
        if (user.getConfirmationStatus() == 0) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            user.setConfirmationStatus(1);
            tx.commit();
            message = "Congratulations! Your account is activitied now. Welcome, " + user.getScreenName();
        } else {
            message = "This account has already been activitied before.";
        }
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
