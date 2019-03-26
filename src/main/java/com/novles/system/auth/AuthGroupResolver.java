package com.novles.system.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novles.system.auth.AuthGroup;
import com.novles.system.auth.AuthGroupService;
import com.novles.system.code.Code;
import com.novles.system.user.User;
import com.novles.system.user.UserService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;

@GraphQLApi
@Component
public class AuthGroupResolver {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthGroupService authGroupService;

    @GraphQLQuery
    public List<AuthGroup> authGroups() {
        return authGroupService.findAll();
    }
    
    @GraphQLQuery
    public Optional<AuthGroup> authGroup(@GraphQLArgument(name = "id") @GraphQLNonNull String id) {
        return authGroupService.findById(id);
    }

    @GraphQLQuery
    public Optional<AuthGroup> authGroup(@GraphQLContext User user) {
        return authGroupService.findById(user.getPrivGrpId());
    }
    
    
}
