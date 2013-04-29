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
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)$";
    private Pattern pattern;
    private Matcher matcher;
    private EntityManager em;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
        em = EMF.createEntityManager();
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("E-mail validation failed.", "Invalid E-mail format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        if (!em.createNamedQuery("User.findByEmail").setParameter("email", value.toString()).getResultList().isEmpty()) {
            FacesMessage message = new FacesMessage("Email not avaiable", "This email has already been registered. Try another?");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
