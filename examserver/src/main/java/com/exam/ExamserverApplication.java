package com.exam;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("Starting code");

			// Create a user
			User user = new User();
			user.setFirstName("Hazeline");
			user.setLastName("Rajput");
			user.setUsername("hazeline87");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setEmail("daniel@com"); // Corrected the email
			user.setProfile("default.png");

			// Create the role
			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			// Assign the role to the user
			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);

			userRoleSet.add(userRole); // Add the UserRole to the set

			// Create the user in the database
			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println("User created: " + user1.getUsername());
		} catch (UserFoundException e) {
			e.printStackTrace();
		}
	}
}
