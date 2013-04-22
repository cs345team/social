/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Madfrog
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    private static final String PASSWORD_PATTERN = ".*(?=.{8,20})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*";
    private Pattern pattern;
    private Matcher matcher;

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Password validation failed.", "Password should be 8-20 chars in length, "
                    + "                              contain at least one digit, one lowercase and one uppercase letter.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

        String password = value.toString();

        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();

        // Let required="true" do its job.
        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            uiInputConfirmPassword.setValid(false);
            throw new ValidatorException(new FacesMessage("Password must match confirm password."));
        }

    }
}
