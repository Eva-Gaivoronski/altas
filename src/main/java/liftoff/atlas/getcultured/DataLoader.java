package liftoff.atlas.getcultured;

import liftoff.atlas.getcultured.models.EmailService;
import liftoff.atlas.getcultured.models.SecureToken;
import liftoff.atlas.getcultured.models.User;
import liftoff.atlas.getcultured.models.data.SecureTokenRepository;
import liftoff.atlas.getcultured.models.data.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            UserRepository userRepository, //
            SecureTokenRepository secureTokenRepository,
            EmailService emailService) {
        return args -> {
            // Create and save entities
            String user1EmailAddress = System.getenv("TEST_USER_EMAIL"); //retrieve Alex's email from the env variable
            User user1 = new User("alex",user1EmailAddress,"testing123");
            userRepository.save(user1);

            SecureToken user1EmailVerificationToken = new SecureToken(user1,"Email verification token");
            secureTokenRepository.save(user1EmailVerificationToken);

            emailService.sendVerificationEmail(
                    user1.getEmailAddress(),
                    "GetCultured - Email sent from SpringBoot project",
                    "This is a test email being sent from my local app. The token value is: " + user1EmailVerificationToken.getTokenValue());
        };
    }
}