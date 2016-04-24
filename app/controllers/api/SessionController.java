package controllers.api;

import domain.Session;
import domain.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.SessionRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.UUID;

public class SessionController extends Controller{

    @Inject
    UserRepository userRepository;

    @Inject
    SessionRepository sessionRepository;

    public Result login() {
        User user = new User(
                request().body().asJson().get("email").asText(),
                request().body().asJson().get("password").asText()
        );

        User storedUser = userRepository.getByIdentifier(user.getEmail());

        if(storedUser == null) {
            UUID authToken = processNewUser(user);
            return created(Json.toJson(new Session(authToken.toString(), user)));
        } else if (storedUser.getPassword().equals(user.getPassword())) {
            UUID authToken = processExistingUser(storedUser);
            return ok(Json.toJson(new Session(authToken.toString(), storedUser)));
        } else {
            return unauthorized();
        }
    }

    private UUID processNewUser(User user) {
        userRepository.add(user);
        UUID authToken = processExistingUser(user);
        return authToken;
    }

    private UUID processExistingUser(User storedUser) {
        UUID authToken = UUID.randomUUID();
        sessionRepository.add(new Session(
                authToken.toString(),
                storedUser
        ));
        return authToken;
    }
}
