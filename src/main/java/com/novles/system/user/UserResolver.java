package com.novles.system.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novles.common.page.PageResult;
import com.novles.common.page.PageSearch;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;

@GraphQLApi
@Component
public class UserResolver {

    @Autowired
    private UserService userService;
    
    @GraphQLQuery
    public PageResult<User> users(@GraphQLArgument(name = "filter") final PageSearch<UserParam> page) {
        
        UserParam param =  (UserParam)page.getParam();
        User user = param.convert(param);
        
        return userService.find(new PageSearch<User>(page.getPage(), page.getSize(), page.getSort(), page.getSortColumn(), page.getSortType(), user));
    }
    
    @GraphQLQuery
    public List<User> usersAll(@GraphQLArgument(name = "filter") final UserParam user) {
        return userService.find(user.convert(user));
    }
    
    
    @GraphQLQuery
    public User user(@GraphQLArgument(name = "id") @GraphQLNonNull String id) {
        return userService.findById(id);
    }
  
}
