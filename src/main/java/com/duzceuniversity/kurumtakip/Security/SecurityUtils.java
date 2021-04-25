package com.duzceuniversity.kurumtakip.Security;

import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Utility class for Spring Security.
 */
@Component
public final class SecurityUtils {
    @Autowired
    private UserRepository userRepository11;

    @PostConstruct
    private void initStaticuser() {
        userRepository = this.userRepository11;
    }

    private static final ModelMapper modelMapper = null;
    private static UserRepository userRepository;

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static User getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserPrincipal userPrincipal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();
        User user = userPrincipal.getKullanici();
        assert false;
        User userNew = userRepository.findById(user.getId());
       /* user.setDeleteAt(userNew.getDeleteAt());
        user.setRoles(userNew.getRoles());
        user.setFirmaBilgileri(userNew.getFirmaBilgileri());*/
        return userNew;
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
//
//    /**
//     * Check if a user is authenticated.
//     *
//     * @return true if the user is authenticated, false otherwise.
//     */
//    public static boolean isAuthenticated() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
//    }
//
//    /**
//     * If the current user has a specific authority (security role).
//     * <p>
//     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
//     *
//     * @param authority the authority to check.
//     * @return true if the current user has the authority, false otherwise.
//     */
//    public static boolean isCurrentUserInRole(String authority) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && getAuthorities(authentication).anyMatch(authority::equals);
//    }
//
//    private static Stream<String> getAuthorities(Authentication authentication) {
//        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
//    }
}
