package com.novles.system.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.novles.common.page.PageResult;
import com.novles.common.page.PageSearch;
import com.novles.system.auth.AuthGroup;
import com.novles.system.user.User;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;

@GraphQLApi
@Component
public class MenuResolver {
//
    @Autowired
    private MenuService menuService;

    @GraphQLQuery
    public List<Menu> menus() {
        return menuService.findAll();
    }
    
    @GraphQLQuery
    public Optional<Menu> menu(@GraphQLArgument(name = "id") @GraphQLNonNull String id) {
        return menuService.findById(id);
    }

    @GraphQLQuery
    public List<Menu> menus(@GraphQLContext AuthGroup authGroup) {
        return menuService.findByAuthGroupId(authGroup.getPrivGrpId());
    }
    
    
   /* @GraphQLQuery
    public List<Menu> menus() {
        return Lists.newArrayList();
    }

    @GraphQLQuery
    public Menu menu(@GraphQLArgument(name = "key", defaultValue = "-1") @GraphQLNonNull String key) {
        return new Menu();
    }
    
    @GraphQLQuery
    public List<Menu> childMenus(@GraphQLContext Menu code) {// @GraphQLArgument(name = "parentCode") Code code) {
        return Lists.newArrayList();
    }

    @GraphQLQuery
    public PageResult<Menu> filterMenus(@GraphQLArgument(name = "filter") PageSearch<Menu> page) {
        Long totalCount = new Long(1);
        return new PageResult<Menu>(totalCount, Lists.newArrayList(), page);
    }*/
}
