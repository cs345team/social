/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
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
@ApplicationScoped
public class ImageController {

    private EntityManager em;
    private StreamedContent profileImage;

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        em = EMF.createEntityManager();
    }

    public StreamedContent getProfileImage() {
        StreamedContent imgStream = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            User user = (User) em.createNamedQuery("User.findById").setParameter("id", Integer.valueOf(id)).getResultList().get(0);
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
    }
}
