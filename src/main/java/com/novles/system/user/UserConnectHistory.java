package com.novles.system.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 사용자접속이력
 * 
 * @author Moon
 *
 */
@Data
@Table(name = "TB_BO_USERCONNHST")
//@Entity
public class UserConnectHistory {

    @Id
    private int connSeq;

    @NotNull
    private String lgnDttm;

    private String lgnIp;

    private String lgoDttm;

    @NotNull
    private String fstRegDttm;

    @NotNull
    private String fstRegrId;

    @NotNull
    private String lstCngDttm;

    @NotNull
    private String lstChgrId;

    @NotNull
    private String userId;

}
