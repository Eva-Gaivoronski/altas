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

    public void sendVerificationEmail(String to, String subject, String content) {
        //TODO: Replace with HTML email
        //TODO: Include link to a page that will check tokenValue query parameter and bounce it against the DB to ensure correct User is in session
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }

    // Handles sending the user verification email to Users on request; throws MessagingException to handle SMTP connection/authentication issues
    public void sendUserVerificationEmailHTML(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(new InternetAddress(System.getenv("APP_EMAIL")));
        message.setRecipients(MimeMessage.RecipientType.TO,to);
        message.setSubject("GetCultured - Please verify your email address");

        //TODO: Add in verification token to link
        String htmlContent =
                "<p>" +
                    "Please click the link to finish verifying your account." +
                    "<br />" +
                    "<a href=\"http://localhost:8080\">" + "Click me!" + "</a>" +
                "</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        emailSender.send(message);
    }

    //TODO: Create similar method for a password reset token
    public void sendPasswordResetEmail() {

    }

}

