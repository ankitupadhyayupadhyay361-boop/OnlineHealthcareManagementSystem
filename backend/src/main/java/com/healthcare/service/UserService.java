package com.healthcare.service;

import com.healthcare.entity.User;
import com.healthcare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {

        if (user.getPassword() != null &&
                !isBCryptPassword(user.getPassword())) {

            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByRole(String role) {

        return userRepository.findByRole(role);
    }

    public List<User> searchUsersByName(String name) {

        return userRepository
                .findByNameContainingIgnoreCase(name);
    }

    public Optional<User> loginUser(String email,
                                    String password) {

        Optional<User> userOptional =
                userRepository.findByEmail(email);

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (passwordEncoder.matches(
                    password,
                    user.getPassword())) {

                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    private boolean isBCryptPassword(String password) {

        return password.startsWith("$2a$")
                || password.startsWith("$2b$")
                || password.startsWith("$2y$");
    }
}
