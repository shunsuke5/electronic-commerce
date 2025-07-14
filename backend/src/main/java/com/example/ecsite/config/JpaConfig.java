package com.example.ecsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<User> auditorAware() {
        return new SpringSecurityAuditor();
    }

    class SpringSecurityAuditor implements AuditorAware<User> {

        @Override
        public Optional<User> getCurrentAuditor() {
            Optional<Object> obj = Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal);

            // TODO: String -> Userの変換処理を自分で実装する
            User user = new User(obj.toString(), "", Collections.emptyList());
            return Optional.of(user);
        }
    }
}
