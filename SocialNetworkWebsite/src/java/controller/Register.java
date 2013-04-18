package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class Register {

    private User user;
    private String invitationCode;
    private boolean valid;
    EntityManager em;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        user = new User();
        em = EMF.createEntityManager();
    }

    public String isCodeValid() {
        valid = true;
        FacesMessage message = new FacesMessage("Congrats!Invitation is valid!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "registration.xhtml?faces-redirect=true&code=" + invitationCode;
    }

    public void submit() {
        user.setUserId(10002);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
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
