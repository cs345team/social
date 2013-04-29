/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import model.User;

import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jichao
 */
@ManagedBean
public class EmailSender {

    private User user;
    private String confirmationCode;

    public EmailSender(User user, String confirmationCode) {
        this.user = user;
        this.confirmationCode = confirmationCode;
    }

    public String sendEmailToUser() {
        final String username = "edu.suffolk.msonws@gmail.com";
        final String password = "socialcs345";
        final int port = 465;
        final String host = "smtp.gmail.com";
        final String from = "edu.suffolk.msonws@gmail.com";
        final String to = user.getEmail();
        String hostName;

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        hostName = request.getHeader("host");

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Authenticator authenticator = new Authenticator() {
            private PasswordAuthentication pa = new PasswordAuthentication(username, password);

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        };
        String result = "";
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(false);
        String htmlContent = "<html>Dear " + user.getScreenName()
                + ",<br><br>Please click the following link to finish your registration:<br><br>"
                + "<a href=\"http://" + hostName
                + "/SocialNetworkWebsite/confirmation.xhtml?confirmationCode="
                + confirmationCode + "\">Click here to confirm</a><br><br>Best,<br>Buttbook </html>";
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject("Confirmation");
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html");
            Multipart multiPart = new MimeMultipart("alternative");
            multiPart.addBodyPart(htmlPart);
            message.setContent(multiPart);
            Transport.send(message);
            result = "Done";
        } catch (MessagingException e) {
            result = e.toString();
            //throw new RuntimeException(e);
        }
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}