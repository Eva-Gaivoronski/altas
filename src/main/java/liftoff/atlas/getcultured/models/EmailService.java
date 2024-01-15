package liftoff.atlas.getcultured.models;

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

    //TODO: Create similar method for a password reset token
    public void sendPasswordResetEmail() {

    }

}

