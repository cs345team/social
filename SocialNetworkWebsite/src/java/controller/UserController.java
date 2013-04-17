package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@SessionScoped
public class UserController {

    private User user;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public void login() {
        FacesMessage message = new FacesMessage("Login succesfully!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void logout() {
    }
}
