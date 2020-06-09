package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author ioannou
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long userId) {
        return userRepository.findOne(userId);
    }
}
