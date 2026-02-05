package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserManagementController {

	private final InMemoryUserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;
	private final List<String> createdUsers = new ArrayList<>();

	public UserManagementController(InMemoryUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
		this.userDetailsManager = userDetailsManager;
		this.passwordEncoder = passwordEncoder;
		createdUsers.add("rajeshmogilipula");
		createdUsers.add("in28minutes");
	}

	@GetMapping
	public String showAdminUserPage(ModelMap model) {
		if (!model.containsAttribute("userForm")) {
			UserForm userForm = new UserForm();
			userForm.setRole("USER");
			model.addAttribute("userForm", userForm);
		}
		model.addAttribute("users", createdUsers.stream().sorted(Comparator.naturalOrder()).toList());
		return "adminUsers";
	}

	@PostMapping
	public String createUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("users", createdUsers.stream().sorted(Comparator.naturalOrder()).toList());
			return "adminUsers";
		}

		if (userDetailsManager.userExists(userForm.getUsername())) {
			model.addAttribute("errorMessage", "User already exists: " + userForm.getUsername());
			model.addAttribute("users", createdUsers.stream().sorted(Comparator.naturalOrder()).toList());
			return "adminUsers";
		}

		UserDetails newUser = User.withUsername(userForm.getUsername())
				.password(passwordEncoder.encode(userForm.getPassword()))
				.roles(userForm.getRole())
				.build();

		userDetailsManager.createUser(newUser);
		createdUsers.add(userForm.getUsername());
		model.addAttribute("successMessage", "Created " + userForm.getRole() + " user: " + userForm.getUsername());

		UserForm blankForm = new UserForm();
		blankForm.setRole("USER");
		model.addAttribute("userForm", blankForm);
		model.addAttribute("users", createdUsers.stream().sorted(Comparator.naturalOrder()).toList());
		return "adminUsers";
	}
}
