package com.deskera.developer.sampleproject.service;

import com.deskera.developer.sampleproject.dto.ConnectUrl;
import com.deskera.developer.sampleproject.entities.UserSample;

public interface UserService {

    void createUser(final UserSample user);

    UserSample getByUserId(final String userName);

    UserSample update(final UserSample user);

    void deleteAll();

    long getUserCount();

    UserSample getFirstUser();
}
