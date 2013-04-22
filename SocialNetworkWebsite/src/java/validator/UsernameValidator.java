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

/**
 *
 * @author Madfrog
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    private static final String USERNAME_PATTERN = "^[[A-Za-z0-9]+[A-Za-z0-9,._-]*]{3,20}$";
    private Pattern pattern;
    private Matcher matcher;

    public UsernameValidator() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Username validation failed.", "Contains 3-20 alphanumeric, dashes and underscores without spaces.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
