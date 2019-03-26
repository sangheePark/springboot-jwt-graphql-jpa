package com.novles.system.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 권한그룹
 * 
 * @author Moon
 *
 */
@Data
@Table(name = "TB_BO_PRMSGR", schema="SRPAYADM")
@Entity
public class AuthGroup {

    @Id
    private String privGrpId;

    @NotNull
    private String privGrpNm;

    @NotNull
    private String privGrpTypeCd;

    private String privGrpAttrCd;

    @NotNull
    private String useYn;

    @NotNull
    private String fstRegDttm;

    @NotNull
    private String fstRegrId;

    @NotNull
    private String lstCngDttm;

    @NotNull
    private String lstChgrId;

}
