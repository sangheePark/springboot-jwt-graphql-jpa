package com.novles.system.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자
 *  
 * @author Moon
 *
 */
@Data
@Table(name = "TB_BO_USER", schema="SRPAYADM")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 6408602719935228942L;

    @Id
    private String userId;

    private String privGrpId;

    @NotNull
    private String userNm;

    @NotNull
    private String userTypeCd;

    @NotNull
    private String userStatusCd;

    private String blngCoNm;

    private String lstLgnDttm;

    @NotNull
    private String pwd;

    // 비밀번홍오류 횟수
    private Integer pwdErrCnt;

    // 비밀번호변경일시
    private String pwdCngDttm;

    // 비밀번호변경자ID
    private String pwdChgrId;

    private String telNo;

    private String mdnNo;
    
    private String mngrEmail;

    private String homeMenuId;

    private String applStrtDt;

    private String applFinDt;

    @NotNull
    private String fstRegDttm;

    @NotNull
    private String fstRegrId;

    @NotNull
    private String lstCngDttm;

    @NotNull
    private String lstChgrId;

    public User(String userId, String userNm, String userTypeCd
            , String blngCoNm, String lstLgnDttm, String telNo, String mdnNo, String mngrEmail
            , String fstRegDttm, String fstRegrId, String lstCngDttm, String lstChgrId) {
        this.userId = userId;        this.userNm = userNm;        this.userTypeCd = userTypeCd;        this.blngCoNm = blngCoNm;
        this.lstLgnDttm = lstLgnDttm;        this.telNo = telNo;        this.mdnNo = mdnNo;        this.mngrEmail = mngrEmail;
        this.fstRegDttm = fstRegDttm;        this.fstRegrId = fstRegrId;        this.lstCngDttm = lstCngDttm;        this.lstChgrId = lstChgrId;
    }
}

