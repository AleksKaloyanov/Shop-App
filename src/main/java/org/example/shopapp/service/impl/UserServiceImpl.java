package org.example.shopapp.service.impl;

import org.example.shopapp.model.entity.UserEntity;
import org.example.shopapp.model.service.UserServiceModel;
import org.example.shopapp.repository.UserRepository;
import org.example.shopapp.service.UserService;
import org.example.shopapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        userRepository.save(modelMapper.map(userServiceModel, UserEntity.class));
    }

    @Override
    public boolean usernameOrEmailExists(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).isPresent();
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void login(Long id) {
        currentUser.setId(id);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
    }

}
