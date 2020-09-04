package com.deskera.developer.sampleproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deskera.developer.sampleproject.dto.UserDetails;
import lombok.Data;

@Data
@Entity
@Table(name = "user_sample")
public class UserSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String userId;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private String userDetails;
}
