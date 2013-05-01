/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class ImageController {

    @ManagedProperty(value = "#{param.id}")
    private Integer id;
    private EntityManager em;
    private StreamedContent profileImage;

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        em = EMF.createEntityManager();
        id = 0;
    }

    public StreamedContent getProfileImage() {
        StreamedContent imgStream = null;
        User user = (User) em.createNamedQuery("User.findById").setParameter("id", id).getResultList().get(0);
        Image img = user.getProfileImg();
        if (img != null) {
            byte[] imgBytes = img.getImg();
            if (imgBytes != null) {
                if (imgBytes.length > 0) {
                    imgStream = new DefaultStreamedContent(new ByteArrayInputStream(imgBytes), "image/png");
                }
            }
        }
        return imgStream;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
