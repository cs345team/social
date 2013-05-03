/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import model.User;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class ViewProfileController {

//    private EntityManager em;
    private User target;

    /**
     * Creates a new instance of ViewProfileController
     */
    public ViewProfileController() {
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }
    
    
}
