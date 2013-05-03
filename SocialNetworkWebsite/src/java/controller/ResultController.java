/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import model.Image;
import model.User;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@ViewScoped
public class ResultController {

    private String keyword;
    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private List<User> users;

    /**
     * Creates a new instance of resultController
     */
    public ResultController() {
        em = EMF.createEntityManager();
        keyword = "";
    }

    @PostConstruct
    public void init() {
        keyword = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("keyword");
        doSearch();
    }

    public void doSearch() {
        users = (List<User>) em.createNamedQuery("User.findByScreenName").setParameter("screenName", keyword).getResultList();
        if (users.isEmpty()) {
            List<User> result = (List<User>) em.createNamedQuery("User.findAll").getResultList();
            for (User u : result) {
                if (u.getInterests() != null) {
                    if (u.getInterests().contains(keyword)) {
                        users.add(u);
                    }
                }
            }
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
