package org.yuequan.xiulvpai.web.service;

import org.yuequan.xiulvpai.common.domain.entity.User;
import org.yuequan.xiulvpai.common.respository.UserRepository;
import org.yuequan.xiulvpai.web.exception.ResourceConflictException;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

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

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent() && (user.getId() == null || user.getId().isBlank())){
            throw new ResourceConflictException("");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User save(User user, Integer[] roleIds){
        user.setRoles(null);
        if(roleIds != null && roleIds.length > 0){
            user.setRoles(new HashSet<>(roleService.findByIdIn(roleIds)));
        }
        return save(user);
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
