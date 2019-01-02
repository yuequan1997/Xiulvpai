package org.yuequan.xiulvpai.security.common.configuration;

import org.yuequan.xiulvpai.common.domain.entity.User;
import org.yuequan.xiulvpai.security.common.configuration.support.registry.AuthorizationRegistry;
import org.yuequan.xiulvpai.security.common.configuration.support.registry.HttpSecurityRegistry;
import org.yuequan.xiulvpai.common.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 公共的Security配置类
 * @author yuequan
 * @since 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableCaching
public class CommonSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityUserDetailsService userDetailsService(){
        return new SecurityUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureHttpSecurityRegistries(http);
        configureAuthorizationRegistries(http.authorizeRequests());
    }

    protected void configureHttpSecurityRegistries(HttpSecurity http){
        var securityRegistries = getApplicationContext().getBeansOfType(HttpSecurityRegistry.class);
        securityRegistries.values().forEach(config -> {
            config.configure(http);
        });
    }

    protected void configureAuthorizationRegistries(ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry authorizeRequests){
        var authorizationRegistries = getApplicationContext().getBeansOfType(AuthorizationRegistry.class);
        authorizationRegistries.values().forEach(config -> {
            config.configure(authorizeRequests);
        });
    }


    class SecurityUserDetailsService implements UserDetailsService{
        @Autowired
        private UserRepository userRepository;

        @Transactional(readOnly = true)
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
            var roles = user.getRoles();
            roles.size(); // non lazy
            return new UserDefaultAndPermission(user);
        }
    }

    class UserDefaultAndPermission implements UserDetails{

        private User user;

        public UserDefaultAndPermission(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorities = new HashSet<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public Set<String> getPermissions(){
            var permissions = new HashSet<String>();
            var roles = user.getRoles();
            roles.forEach(role -> role.getPermissions().forEach(permission -> permissions.add(permission.getPath())));
            return permissions;
        }
    }
}
