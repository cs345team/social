package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import model.User;
import server.EMF;

/**
 *
 * @author mcsadmin
 */
@ManagedBean
@SessionScoped
public class UserController {

    private User user;
    private String screenName;
    private String passWd;
    private EntityManager em;
    private boolean isLoggedIn = false;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        em = EMF.createEntityManager();
    }

    public String login() {
        em = EMF.createEntityManager();
        user = (User) em.createNamedQuery("User.findByScreenName").setParameter("screenName", screenName).getResultList().get(0);

        if (user == null) {
            FacesMessage message = new FacesMessage("User name does not exist!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (!user.getPasswd().equals(passWd)) {
            FacesMessage message = new FacesMessage("User name and password dose not match!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            isLoggedIn = true;
            FacesMessage message = new FacesMessage("Login succesfully!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "/user/home.xhtml?faces-redirect=true";
        }
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }
}
