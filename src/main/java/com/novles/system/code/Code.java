package com.novles.system.code;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "TB_COMM_CODE", schema="SRPAYADM")
@IdClass(CodePk.class)
public class Code implements Serializable {
    private static final long serialVersionUID = -3537028104790397812L;

    @Id
    @Column(name = "CD_GRP_NM")
    private String key;

    @Id
    @Column(name = "CD_VAL")
    private String value;

    @Column(name = "CD_GRP_NM_DESC")
    private String keyName;

    @Column(name = "CD_VAL_DESC")
    private String label;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "REG_DTTM")
    private String regDate;

    @Column(name = "LST_UPDATE_DTTM")
    private String updateDate;
}
