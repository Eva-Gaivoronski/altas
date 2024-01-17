package liftoff.atlas.getcultured.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    // TODO: Remove after removing from DataLoader
    public void sendVerificationEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }

    // Handles sending the user verification email to Users on request
    public void sendUserVerificationEmailHTML(String to, String tokenValue) {

        try {
            MimeMessage message = emailSender.createMimeMessage();

            message.setFrom(new InternetAddress(System.getenv("APP_EMAIL")));
            message.setRecipients(MimeMessage.RecipientType.TO,to);
            message.setSubject("GetCultured - Please verify your email address");

            String htmlContent =
                    "<p>" +
                        "Please click the link below to finish verifying your account: <br />" +
                        "<a href=\"http://localhost:8080/user/verifyEmail/" + tokenValue +"\">" + "Click me to verify your account! </a>" +
                    "</p>";
            message.setContent(htmlContent, "text/html; charset=utf-8");

            emailSender.send(message);

        }

        // Catches MessagingException to handle SMTP connection/authentication issues
        catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void sendPasswordResetEmailHTML(String to, String tokenValue) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            message.setFrom(new InternetAddress(System.getenv("APP_EMAIL")));
            message.setRecipients(MimeMessage.RecipientType.TO,to);
            message.setSubject("GetCultured - Forgot My Password Request");

            String htmlContent =
                    "<p>" +
                        "Please click the link below to confirm you want to reset your password: <br />" +
                        "<a href=\"http://localhost:8080/user/password-reset/" + tokenValue +"\">" + "Click me to reset password!</a>" +
                    "</p>";
            message.setContent(htmlContent, "text/html; charset=utf-8");

            emailSender.send(message);

        }

        // Catches MessagingException to handle SMTP connection/authentication issues
        catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}

