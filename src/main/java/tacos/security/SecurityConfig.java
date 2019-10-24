package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Marina Baban
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * In memory user store
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}pass1")
                .authorities("ROLE_USER")
                .and()
                .withUser("user2")
                .password("{noop}pass2")
                .authorities("ROLE_USER");

    }*/

    /**
     * JDBC based user store
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from Users where username=?")
                .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username=?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/

    /**
     * LDAP user store
     */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .contextSource()
                    .root("dc=tacocloud,dc=com")
                    .ldif("classpath:users.ldif")
                .and()
                .passwordCompare()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .passwordAttribute("passcode");
    }*/
}
