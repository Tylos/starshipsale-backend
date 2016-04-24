package controllers.authentication;


import com.google.inject.Inject;
import domain.Session;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repositories.SessionRepository;

import javax.annotation.Nullable;

public class RequiredAuthenticator extends Security.Authenticator {

    public static final String AUTHENTICATED_USER_KEY = "authenticated_user";

    @Inject
    SessionRepository sessionRepository;

    @Override
    public String getUsername(Http.Context ctx) {
        String token = getTokenFromHeader(ctx);

        if (token != null) {
            Session session = sessionRepository.getByIdentifier(token);
            if (session != null) {
                ctx.request().withUsername(session.getUser().getEmail());
                return session.getUser().getEmail();
            }
        }

        return null;
    }

    private @Nullable String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized();
    }
}
