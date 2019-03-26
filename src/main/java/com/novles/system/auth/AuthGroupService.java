package com.novles.system.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthGroupService {

    @Autowired
    private AuthGroupRepository authGroupRepository;

    public List<AuthGroup > findAll() {
        return authGroupRepository.findAll();
    }
    
    public Optional<AuthGroup> findById(String id) {
        return authGroupRepository.findById(id);
    }
   
}
