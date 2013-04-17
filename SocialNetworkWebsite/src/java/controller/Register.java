package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@RequestScoped
public class Register {

    private User user;
    private String invitationCode;
    private boolean valid;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        user = new User();
    }

    public void isCodeValid() {
        valid = true;
        FacesMessage message = new FacesMessage("Congrats!Invitation is valid!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void submit() {
        FacesMessage message = new FacesMessage("Registration succesful!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public User getUser() {
        return user;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public boolean getValid() {
        return valid;
    }
}
