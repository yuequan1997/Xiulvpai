package com.yuequan.xiulvpai.web.service;

import com.yuequan.xiulvpai.common.domain.entity.User;
import com.yuequan.xiulvpai.common.respository.UserRepository;
import com.yuequan.xiulvpai.web.exception.ResourceConflictException;
import com.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link User} Service
 * @author yuequan
 * @since 1.0
 **/
@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent() && (user.getId() == null || user.getId().isBlank())){
            throw new ResourceConflictException("");
        }
        return userRepository.save(user);
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> {throw new ResourceNotFoundException("");});
    }

    @Transactional
    public void destroy(User user){
        userRepository.delete(user);
    }

    @Transactional
    public void destroy(String id){
        var user = userRepository.findById(id);
        if(user.isPresent()){
            destroy(user.get());
        }
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
