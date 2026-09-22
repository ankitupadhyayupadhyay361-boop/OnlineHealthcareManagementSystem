package com.healthcare.controller;

import com.healthcare.entity.User;
import com.healthcare.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(
            @PathVariable String email) {

        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(
            @PathVariable String role) {

        return userService.getUsersByRole(role);
    }

    @GetMapping("/search/{name}")
    public List<User> searchUsersByName(
            @PathVariable String name) {

        return userService.searchUsersByName(name);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User loginUser) {

        Optional<User> user =
                userService.loginUser(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                );

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity
                .status(401)
                .body("Invalid email or password");
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        return userService.getUserById(id)
                .map(existingUser -> {

                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setRole(user.getRole());

                    /*
                     * Password tabhi update hoga
                     * jab new password diya gaya ho.
                     */
                    if (user.getPassword() != null
                            && !user.getPassword().isBlank()) {

                        existingUser.setPassword(
                                user.getPassword()
                        );
                    }

                    return ResponseEntity.ok(
                            userService.saveUser(existingUser)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        if (userService.getUserById(id).isPresent()) {

            userService.deleteUser(id);

            return ResponseEntity.ok(
                    "User deleted successfully"
            );
        }

        return ResponseEntity.notFound().build();
    }
}
