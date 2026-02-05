package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails adminUser = createNewUser("rajeshmogilipula", "rajesh@123", "ADMIN", "USER");
		UserDetails normalUser = createNewUser("in28minutes", "dummy", "USER");
		return new InMemoryUserDetailsManager(adminUser, normalUser);
	}

	private UserDetails createNewUser(String username, String password, String... roles) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		return User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles(roles).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
		return http.build();
	}
}
