package com.duzceuniversity.kurumtakip.Security;

import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsernameAndDeleteAtIsNull(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
