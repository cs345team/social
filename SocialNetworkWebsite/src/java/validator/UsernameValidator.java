/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import server.EMF;

/**
 *
 * @author Xi
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    private static final String USERNAME_PATTERN = "^[[A-Za-z0-9]+[A-Za-z0-9,._-]*]{3,20}$";
    private Pattern pattern;
    private Matcher matcher;
    private EntityManager em;

    public UsernameValidator() {
        pattern = Pattern.compile(USERNAME_PATTERN);
        em = EMF.createEntityManager();
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Username validation failed.", "Contains 3-20 alphanumeric, dashes and underscores without spaces.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        if (!em.createNamedQuery("User.findByScreenName").setParameter("screenName", value.toString()).getResultList().isEmpty()) {
            FacesMessage message = new FacesMessage("Username not avaiable", "Someone already has that username. Try another?");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
