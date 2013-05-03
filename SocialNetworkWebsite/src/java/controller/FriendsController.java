/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import model.Friends;
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
@RequestScoped
public class FriendsController {

    
    @ManagedProperty(value = "#{userController.user}")
    private User user;
    private EntityManager em;
    private List<Friends> users;
    private int i = 0;

    /**
     * Creates a new instance of resultController
     */
    public FriendsController() {
        em = EMF.createEntityManager();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Friends> getUsers() {
        users = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", user).getResultList();
        return users;
    }

    public void setUsers(List<Friends> users) {
        this.users = users;
    }

/*
    public StreamedContent getProfileImage() {
        User u = users.get(i);
        Image img = u.getProfileImg();
        DefaultStreamedContent imgStream = new DefaultStreamedContent();
        if (img != null) {
            byte[] imgBytes = img.getImg();
            if (imgBytes != null) {
                if (imgBytes.length > 0) {
                    imgStream = new DefaultStreamedContent(new ByteArrayInputStream(imgBytes), "image/png");
                }
            }
        }
        i++;
        return imgStream;

    }*/
}