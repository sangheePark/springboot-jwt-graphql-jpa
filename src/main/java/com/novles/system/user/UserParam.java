package com.novles.system.user;

import java.io.Serializable;

import lombok.Data;

/**
 * 사용자 조회 파라미터 
 * @author Moon
 *
 */
@Data
public class UserParam  implements Serializable {

    private static final long serialVersionUID = 7025085090218825449L;

    private String userId;

    private String userNm;

    private String userTypeCd;

    private String userStatusCd;

    private String blngCoNm;

    private String lstLgnDttm;

    private String telNo;

    private String mdnNo;

    private String mngrEmail;

    private String fstRegDttm;

    private String fstRegrId;

    private String lstCngDttm;

    private String lstChgrId;
    
    public static User convert(UserParam user) {
        return user != null ? new User(user.getUserId(), user.getUserNm(), user.getUserTypeCd()
                , user.getBlngCoNm(), user.getLstLgnDttm(), user.getTelNo(), user.getMdnNo(), user.getMngrEmail()
                , user.getFstRegDttm(), user.getFstRegrId(), user.getLstCngDttm(), user.getLstChgrId()) : null;
    }

}
