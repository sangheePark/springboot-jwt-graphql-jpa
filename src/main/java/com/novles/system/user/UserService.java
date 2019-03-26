package com.novles.system.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.novles.common.page.PageResult;
import com.novles.common.page.PageSearch;
import com.novles.system.code.Code;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> find(User filter) {
        
        if(filter == null) {
            return userRepository.findAll();
        }
        
        //ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return StreamSupport.stream(userRepository.findAll(Example.of(filter, matcher)).spliterator(), false)
                .collect(Collectors.toList());
    }
    
    public PageResult<User> find(PageSearch<User> pageSearch) {

//      List<Code> result = Lists.newArrayList();

      log.error("xx:" + new Gson().toJson(pageSearch.getParam()));

      ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

      Example<User> example = Example.of(Optional.ofNullable(pageSearch.getParam()).orElse(new User()), matcher);
      Page<User> page = userRepository.findAll(example, pageSearch.buildPageRequest());
      
      return new PageResult<User>(page);
  }
    
    
    public User findById(String id) {
        return userRepository.findById(id).get();
    }
   
}
