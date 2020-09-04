package com.deskera.developer.sampleproject.service.impl;

import com.deskera.developer.sampleproject.entities.UserSample;
import com.deskera.developer.sampleproject.repository.UserRepository;
import com.deskera.developer.sampleproject.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserSample user) {
        this.userRepository.save(user);
    }

    @Override
    public UserSample getByUserId(final String userId) {
        final Optional<UserSample> optionalUser = this.userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public UserSample update(final UserSample user) {
        final UserSample userSample = this.getByUserId(user.getUserId());
        if (userSample == null) {
            return this.userRepository.save(user);
        }
        user.setId(userSample.getId());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteAll() {
        this.userRepository.deleteAll();
    }

    @Override
    public long getUserCount() {
        return this.userRepository.count();
    }

    @Override
    public UserSample getFirstUser() {
        final UserSample firstUser = this.userRepository.findAll().get(0);
        return firstUser;
    }
}
