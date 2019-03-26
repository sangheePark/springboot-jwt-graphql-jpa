package com.novles.system.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novles.common.page.PageResult;
import com.novles.common.page.PageSearch;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;

@GraphQLApi
@Component
public class CodeResolver {

    @Autowired
    private CodeService codeService;

    @GraphQLQuery
    public List<Code> codes() {
        return codeService.findAll();
    }

    @GraphQLQuery
    public Code code(@GraphQLArgument(name = "key", defaultValue = "-1") @GraphQLNonNull String key) {
        return codeService.findByKey(key);
    }
    
    @GraphQLMutation
    public Code addCode(@GraphQLArgument(name = "code") Code code) {
        return codeService.add(code);
    }
    
    @GraphQLMutation
    public Boolean editCode(@GraphQLArgument(name = "code") Code code) {
        codeService.edit(code);
        return true;
    }
    
    @GraphQLMutation
    public Boolean removeCodeById(@GraphQLArgument(name = "id") CodePk codePk) {
        codeService.removeById(codePk);
        return true;
    }

    @GraphQLQuery
    public List<Code> childCodes(@GraphQLContext Code code) {// @GraphQLArgument(name = "parentCode") Code code) {
        return codeService.findByCode(code);
    }

    @GraphQLQuery
    public PageResult<Code> filterCodes(@GraphQLArgument(name = "filter") PageSearch<Code> page) {
        return codeService.findByFilter(page);
    }

    @GraphQLQuery
    public PageResult<Code> filterCodesJPA(@GraphQLArgument(name = "filter") PageSearch<Code> page) {
        return codeService.findByFilterJPA(page);
    }
}
