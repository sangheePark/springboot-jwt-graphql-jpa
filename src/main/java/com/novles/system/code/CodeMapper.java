package com.novles.system.code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.novles.common.page.PageSearch;

@Mapper
public interface CodeMapper {

    List<Code> findAll();

    List<Code> findByCode(Code code);

    Code findByKey(String key);

    List<Code> findByFilter(PageSearch<Code> page);

    Long findCountByFilter(PageSearch<Code> page);

}
