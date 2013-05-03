/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Image;
import model.User;
import model.Wall;
import org.primefaces.event.FileUploadEvent;
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
    @ManagedProperty(value = "#{userController.target}")
    private User target;
    private EntityManager em;
    private Wall newFeed;

    /**
     * Creates a new instance of FeedController
     */
    public FeedController() {
        user = new User();
        target = new User();
        em = EMF.createEntityManager();
        newFeed = new Wall();
    }

    public void post() {
        if ((newFeed.getText().isEmpty()) && !newFeed.hasImage()) {
            FacesMessage msg = new FacesMessage("Please input something!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            EntityTransaction tx = em.getTransaction();
            if(newFeed.hasImage()) {
                Image img = newFeed.getImage();
                tx.begin();
                em.persist(img);
                tx.commit();
            }
            tx = em.getTransaction();
            tx.begin();
            em.persist(newFeed);
            newFeed.setPoster(user);
            newFeed.setUser(user);
            newFeed.setTime(new Date());
            tx.commit();
            newFeed = new Wall();
            FacesMessage msg = new FacesMessage("Posted!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
     public void postToOthers() {
        if ((newFeed.getText().isEmpty()) && !newFeed.hasImage()) {
            FacesMessage msg = new FacesMessage("Please input something!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            EntityTransaction tx = em.getTransaction();
            if(newFeed.hasImage()) {
                Image img = newFeed.getImage();
                tx.begin();
                em.persist(img);
                tx.commit();
            }
            tx = em.getTransaction();
            tx.begin();
            em.persist(newFeed);
            newFeed.setPoster(user);
            newFeed.setUser(target);
            newFeed.setTime(new Date());
            tx.commit();
            newFeed = new Wall();
            FacesMessage msg = new FacesMessage("Posted!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void addImage(FileUploadEvent event) {
        byte[] imgBytes = event.getFile().getContents();
        Image img = new Image();
        img.setImg(imgBytes);
        
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

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
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
