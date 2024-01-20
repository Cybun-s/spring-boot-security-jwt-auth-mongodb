package com.bezkoder.spring.jwt.mongodb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.security.jwt.JwtUtils;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {

	@Autowired
	UserRepository userRepository;

	@DeleteMapping("/by-id/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable String id) {
		this.userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/admin/get-all-users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		return ResponseEntity.ok("valid");
	}
	
}
