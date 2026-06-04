package com.qm.quantitymeasurement.service;

import com.qm.quantitymeasurement.dto.AuthRequest;
import com.qm.quantitymeasurement.dto.AuthResponse;
import com.qm.quantitymeasurement.dto.RegisterRequest;
import com.qm.quantitymeasurement.entity.UserEntity;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    AuthResponse refreshToken(String refreshToken);
    UserEntity getUserByEmail(String email);
}
