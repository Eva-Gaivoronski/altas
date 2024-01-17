package liftoff.atlas.getcultured;

import liftoff.atlas.getcultured.services.EmailService;
import liftoff.atlas.getcultured.models.SecureToken;
import liftoff.atlas.getcultured.models.User;
import liftoff.atlas.getcultured.models.UserGroup;
import liftoff.atlas.getcultured.models.data.SecureTokenRepository;
import liftoff.atlas.getcultured.models.data.UserGroupRepository;
import liftoff.atlas.getcultured.models.data.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class DataLoader {

    private boolean sendVerificationEmailEnabled = false; // Toggle to have the email verification email below send on run

    @Bean
    CommandLineRunner loadData(
            UserGroupRepository userGroupRepository,
            UserRepository userRepository, //
            SecureTokenRepository secureTokenRepository,
            EmailService emailService) {
        return args -> {

            /* Begin application data initialization for the User System */
            List<UserGroup> userGroups = new ArrayList<>();

            // TODO: Add logic for these to be skipped if they already exist in the DB. This is important for when we set spring.jpa.hibernate.ddl-auto back to 'update'
            UserGroup adminUG = new UserGroup("admin");
            UserGroup verifiedUG = new UserGroup("verified");
            UserGroup registeredUG = new UserGroup("registered");

            userGroups.add(adminUG);
            userGroups.add(verifiedUG);
            userGroups.add(registeredUG);

//            // UserGroups cannot be referenced below directly this way, and Lazy fetch causes errors thrown when attempting to retrieve from DB.
//            userGroups.add(new UserGroup("admin"));
//            userGroups.add(new UserGroup("verified"));
//            userGroups.add(new UserGroup("registered"));

            userGroupRepository.saveAll(userGroups);
            /* End application data initialization for the User System */


//            String user1EmailAddress = System.getenv("TEST_USER_EMAIL");
            User user1 = new User(
                    "alex",
                    System.getenv("TEST_USER_EMAIL"), //retrieve Alex's email from the env variable
                    "testing123");

            user1.addUserGroupToUser(adminUG);
            user1.addUserGroupToUser(registeredUG);
            user1.addUserGroupToUser(verifiedUG);
            userRepository.save(user1);

//            user1.removeUserGroupFromUser(adminUG);
//            userRepository.save(user1);


            if(sendVerificationEmailEnabled) {
            SecureToken user1EmailVerificationToken = new SecureToken(user1);
            user1EmailVerificationToken.setTypeVerify();
            secureTokenRepository.save(user1EmailVerificationToken);

                emailService.sendVerificationEmail(
                        user1.getEmailAddress(),
                        "GetCultured - Email sent from SpringBoot project",
                        "This is a test email being sent from my local app. The token value is: " + user1EmailVerificationToken.getTokenValue());
            }

        };
    }
}