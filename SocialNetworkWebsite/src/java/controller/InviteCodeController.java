/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class InviteCodeController {

    EntityManager em;
    private String invitationCode;
    private Boolean valid;

    /**
     * Creates a new instance of codeValidate
     */
    public InviteCodeController() {
        em = EMF.createEntityManager();
    }

    public String isCodeValid() {
        valid = true;
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().put("valid", valid.toString());
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("inviteCodeController", this);
        FacesMessage message = new FacesMessage("Congrats!Invitation is valid!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "registration.xhtml";
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
