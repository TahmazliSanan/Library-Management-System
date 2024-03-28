package org.pronet.services;

import org.pronet.entities.User;
import org.pronet.payloads.RegistrationDto;

public interface UserService {
    User findUserByUsername(String username);
    void registerUser(RegistrationDto registrationDto);
}