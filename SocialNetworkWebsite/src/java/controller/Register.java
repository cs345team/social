package controller;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.InviteCode;
import model.User;
import server.EMF;
import server.EmailSender;

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
    private String confPassword;
    private Boolean registered;
    private InviteCode code;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        user = new User();
        em = EMF.createEntityManager();
        valid = false;
    }

    public void submit() {
        if (valid == true) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            em.remove(code);
            tx.commit();
            registered = true;
            EmailSender es = new EmailSender(user, inviteCode);
            String result = es.sendEmailToUser();
            System.out.println(result);

            //FacesMessage message = new FacesMessage(result);
            FacesMessage message = new FacesMessage("Registration succesful!");
            FacesContext.getCurrentInstance().addMessage(null, message);

            valid = false;
        } else {
            registered = false;
            FacesMessage message = new FacesMessage("Please check your invitation code valid or not!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


    }

    public void isCodeValid() {
        List<InviteCode> codes = (List<InviteCode>) em.createNamedQuery("InviteCode.findByCode").setParameter("code", inviteCode).getResultList();
        Date now = new Date();
        if ((!codes.isEmpty()) && (now.getTime() - codes.get(0).getTime().getTime()) > 30 * 24 * 60 * 60 * 1000) {
            code = codes.get(0);
            valid = true;
            FacesMessage message = new FacesMessage("Congrats!Invitation Code is valid!");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), message);
        } else {
            valid = false;
            FacesMessage message = new FacesMessage("Sorry!Invitation Code is not valid or already expired.");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), message);
        }

//           //For test
//            valid = true;
//            FacesMessage message = new FacesMessage("Congrats!Invitation Code is valid!");
//            FacesContext.getCurrentInstance().addMessage(component.getClientId(), message);
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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }
}
