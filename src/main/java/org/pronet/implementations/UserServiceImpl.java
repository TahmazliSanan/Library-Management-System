package org.pronet.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.entities.Role;
import org.pronet.entities.User;
import org.pronet.payloads.RegistrationDto;
import org.pronet.repositories.RoleRepository;
import org.pronet.repositories.UserRepository;
import org.pronet.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        User user = new User();
        Role role = roleRepository.findRoleByName("User");
        user.setFullName(registrationDto.getFullName());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setBirthDate(registrationDto.getBirthDate());
        user.setRoles(List.of(role));
        userRepository.save(user);
    }
}