/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Friends;
import model.Image;
import model.User;
import model.Wall;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import server.EMF;

/**
 *
 * @author Madfrog
 */
@ManagedBean
@RequestScoped
public class PostController {

    
    //@ManagedProperty(value = "#{userController.user}")
    private Wall post;
    private EntityManager em;
    private List<Wall> posts;
    private int i = 0;

    /**
     * Creates a new instance of resultController
     */
    public PostController() {
        em = EMF.createEntityManager();
        post=new Wall();
    }

    public Wall getPost() {
        return post;
    }
    public void doPost(){
        
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(post);
            tx.commit();
            FacesMessage message = new FacesMessage("Post succesful!");
            FacesContext.getCurrentInstance().addMessage(null, message);


    }
    public void setPost(Wall post) {
        this.post = post;
    }

    public List<Wall> getPosts() {
        posts = (List<Wall>) em.createNamedQuery("Wall.findAll").getResultList();
        return posts;
    }

    public void setPosts(List<Wall> posts) {
        this.posts = posts;
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
