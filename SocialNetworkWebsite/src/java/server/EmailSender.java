/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import model.User;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jichao
 */
public class EmailSender {

    public static void sendEmailToUser(User user, String code) {
        final String username = "edu.suffolk.msonws@gmail.com";
        final String password = "socialcs345";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("nijixuchao@gmail.com"));
            message.setSubject("Confirmation");
            message.setText("Dear "
                    + "\n\nPlease click the following link to finish your registration:\n\n"
                    + "<a href=\"http://localhost:8084/SocialNetworkWebsite/confirmation.xhtml?code=" + code + "\">confirm</a> ");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
