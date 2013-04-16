/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@RequestScoped
public class UserController {

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public void signIn() {
        FacesMessage message = new FacesMessage("Registration succesful!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
