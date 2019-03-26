package com.novles.system.code;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.novles.common.page.PageResult;
import com.novles.common.page.PageSearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeRepository codeRepository;

    public List<Code> findAll() {
        return codeMapper.findAll();
    }

    public List<Code> findByCode(Code code) {
        return codeMapper.findByCode(code);
    }

    public Code findByKey(String key) {
        return codeMapper.findByKey(key);
    }

    public PageResult<Code> findByFilter(PageSearch<Code> pageSearch) {
        return new PageResult<Code>(codeMapper.findCountByFilter(pageSearch), codeMapper.findByFilter(pageSearch));
    }

    public PageResult<Code> findByFilterJPA(PageSearch<Code> pageSearch) {

//        List<Code> result = Lists.newArrayList();

        log.error("xx:" + new Gson().toJson(pageSearch.getParam()));
//        ExampleMatcher matcher = ExampleMatcher.matchingAny()
//                .withIgnoreNullValues()

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
//                .withMatcher("key",  GenericPropertyMatcher.of(StringMatcher.DEFAULT))
//                .withMatcher("label", GenericPropertyMatcher.of(StringMatcher.STARTING));
//                .withMatcher("value", matcherConfigurer);

        Example<Code> example = Example.of(Optional.ofNullable(pageSearch.getParam()).orElse(new Code()), matcher);
        Page<Code> page = codeRepository.findAll(example, pageSearch.buildPageRequest());
        
        return new PageResult<Code>(page);
    }

    public Code add(Code code) {
        return codeRepository.save(code);
    }

    public void edit(Code code) {
        codeRepository.save(code);
    }

    public void removeById(CodePk id) {
        codeRepository.deleteById(id);
    }
}
