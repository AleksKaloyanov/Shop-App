package org.example.shopapp.service;

import org.example.shopapp.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);


    boolean usernameOrEmailExists(String username, String email);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(Long id);

    void logout();
}
