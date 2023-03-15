package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuth customUsernamePasswordAuth;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuth customUsernamePasswordAuth) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuth = customUsernamePasswordAuth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        da nema third party app vo nasta app
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/courses", "/AddStudent", "/StudentEnrollmentSummary", "/register").permitAll()
                .antMatchers("/courses/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/courses", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("bojan.dimitrovski")
//                //nesmee da se koriste plain text passwords
//                //treba da imame nekoj password encoder
//                .password(passwordEncoder.encode("bd"))
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .authorities("ROLE_ADMIN");

        auth.authenticationProvider(customUsernamePasswordAuth);

    }

}