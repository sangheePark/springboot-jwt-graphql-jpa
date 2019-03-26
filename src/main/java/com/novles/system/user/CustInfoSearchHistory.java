package com.novles.system.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * 고객정보조회이력
 * 
 * @author Moon
 *
 */
@Data
@Table(name = "TB_BO_CUSTINFO_INQ")
//@Entity
public class CustInfoSearchHistory {

    private String occrDt;

    private String occrSeq;

    private String occrTm;

    private String systemIp;

    private String userId;

    private String userIp;

    private String email;

    private String userNm;

    private String scrnId;

    private String scrnFuncCd;

    private String custListCnt;

}
