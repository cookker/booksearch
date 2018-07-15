package com.ms.ex.booksearch.booksearch;

import com.ms.ex.booksearch.booksearch.config.CustomUserDetails;
import com.ms.ex.booksearch.booksearch.entity.Role;
import com.ms.ex.booksearch.booksearch.entity.User;
import com.ms.ex.booksearch.booksearch.repository.UserRepository;
import com.ms.ex.booksearch.booksearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EntityScan(basePackages = "com.ms.ex.booksearch.booksearch.entity")
public class BooksearchApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BooksearchApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder,
									  UserRepository repository,
									  UserService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new User("admin", "1234", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ADMIN"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
}
