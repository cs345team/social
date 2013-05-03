/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Image;
import model.User;
import model.Wall;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import server.EMF;

/**
 *
 * @author jichao
 */
@ManagedBean
@ViewScoped
public class FeedController {

    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private Wall newFeed;

    /**
     * Creates a new instance of FeedController
     */
    public FeedController() {
        user = new User();
        em = EMF.createEntityManager();
        newFeed = new Wall();
    }

    public void post() {
        if ((newFeed.getText().isEmpty()) && newFeed.getImage() == null) {
            FacesMessage msg = new FacesMessage("Please input something!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(newFeed);
            newFeed.setPoster(user);
            newFeed.setUser(user);
            newFeed.setTime(new Date());
            tx.commit();
            FacesMessage msg = new FacesMessage("Posted!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            newFeed.setText("");
            newFeed.setImage(null);
        }
    }

    public void addImage(FileUploadEvent event) {
        byte[] imgBytes = event.getFile().getContents();
        Image img = new Image();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(img);
        img.setImg(imgBytes);
        tx.commit();
        newFeed.setImage(img);
        FacesMessage msg = new FacesMessage("An image added!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wall getNewFeed() {

        return newFeed;
    }

    public void setNewFeed(Wall newFeed) {
        this.newFeed = newFeed;
    }

    public Boolean imageAttached() {
        if (this.newFeed.hasImage()) {
            return true;
        } else {
            return false;
        }
    }
}
