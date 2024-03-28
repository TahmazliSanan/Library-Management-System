package org.pronet.components;

import lombok.RequiredArgsConstructor;
import org.pronet.entities.Role;
import org.pronet.entities.User;
import org.pronet.repositories.RoleRepository;
import org.pronet.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findRoleByName("Admin");
        Role userRole = roleRepository.findRoleByName("User");
        if (adminRole == null || userRole == null) {
            adminRole = new Role("Admin");
            userRole = new Role("User");
            roleRepository.saveAll(List.of(adminRole, userRole));
        }

        User adminUser = userRepository.findUserByUsername("AdminOfPronet");
        if (adminUser == null) {
            adminUser = new User(1L, "Admin",
                    "AdminOfPronet", passwordEncoder.encode("AdminOfPronet2024"));
            adminUser.setRoles(List.of(adminRole));
            userRepository.save(adminUser);
        }
    }
}