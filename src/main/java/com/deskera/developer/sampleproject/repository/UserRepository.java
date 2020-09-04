package com.deskera.developer.sampleproject.repository;

import com.deskera.developer.sampleproject.entities.UserSample;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserSample, Long> {

    /**
     * Find user by userName
     * @param userId
     * @return
     */
    Optional<UserSample> findByUserId(final String userId);

}
