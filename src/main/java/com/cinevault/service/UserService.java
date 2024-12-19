package com.cinevault.service;

import com.cinevault.entity.User;
import com.cinevault.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public void deteleUser(Long id) {
//        userRepository.deleteById(id);
//    }

}
