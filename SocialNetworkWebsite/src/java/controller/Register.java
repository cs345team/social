package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.User;
import server.EMF;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@ViewScoped
public class Register {

    private User user;
    EntityManager em;
    private String inviteCode;
    private Boolean valid;
    private UIComponent component;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        user = new User();
        em = EMF.createEntityManager();
    }

    public void submit() {
//        user.setUserId(10001);
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        em.persist(user);
//        tx.commit();
        FacesMessage message;
        message = new FacesMessage("Registration succesful!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void isCodeValid() {
        valid = true;
        FacesMessage message = new FacesMessage("Congrats!Invitation is valid!");
        FacesContext.getCurrentInstance().addMessage(component.getClientId(), message);
    }

    public User getUser() {
        return user;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
