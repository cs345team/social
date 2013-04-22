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
import javax.faces.context.FacesContext;
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
    private String userName;
    @ManagedProperty(value = "#{param.code}")
    private String code;

    /**
     * Creates a new instance of ConfirmController
     */
    public ConfirmController() {
        em = EMF.createEntityManager();
        code = "";
    }

    public Boolean confirm() {
//        List<User> list = (ArrayList<User>)em.createNamedQuery("User.findByConfirmationCode").setParameter("confirmationCode", "123").getResultList();
        //User user = new User();
        //user.setScreenName("asd");
        List<User> list = new ArrayList<User>();
        if (list.size() > 0) {
           // changeUserConfirmStatus(user);
            User user = list.get(0);
            userName = user.getScreenName();
            return true;
        } else {
            return false;
        }
        //return false;
    }

    public void changeUserConfirmStatus(User user) {
        if (user.getConfirmationStatus() == 0) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            user.setConfirmationStatus(1);
            tx.commit();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        confirm();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
