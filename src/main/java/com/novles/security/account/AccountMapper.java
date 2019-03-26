package com.novles.security.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    List<Account> findAll();

    Account findBySeqno(Long seqno);

    Account findById(String id);
}
