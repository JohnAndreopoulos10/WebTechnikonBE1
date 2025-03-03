package gr.codehub.jakartaeshop.security;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Base64;

@Provider
public class AuthenticationFilter implements jakarta.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) {
//        Method method = resourceInfo.getResourceMethod();
//        
//
//        if (!method.isAnnotationPresent(PermitAll.class)) {
//            if (method.isAnnotationPresent(DenyAll.class)) {
//                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
//                        .entity("Access blocked for all users !!").build());
//                return;
//            }
//
//            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
//
//            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
//
//            if (authorization == null || authorization.isEmpty()) {
//                requestContext
//                        .abortWith(Response
//                                .status(Response.Status.UNAUTHORIZED)
//                                .entity("You cannot access this resource")
//                                .build());
//                return;
//            }
//
//            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//
//            String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));
//
//            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
//            final String username = tokenizer.nextToken();
//            final String password = tokenizer.nextToken();
//
//            if (method.isAnnotationPresent(RolesAllowed.class)) {
//                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//                Set<String> rolesSetForTheResource = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
//
//                if (!isUserAllowed(username, password, rolesSetForTheResource)) {
//                    requestContext.abortWith(Response
//                            .status(Response.Status.UNAUTHORIZED)
//                            .entity("You cannot access this resource")
//                            .build());
//                }
//            }
//        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSetExpected) {
        boolean isAllowed = false;

        if (username.equals("admin") && password.equals("password")) {
            String userRole = "ADMIN"; 
            if (rolesSetExpected.contains(userRole)) {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}

