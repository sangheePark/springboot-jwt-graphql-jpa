package com.novles.system.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 화면
 * 
 * @author Moon
 *
 */
@Table(name = "TB_BO_SCRN")
@Data
//@Entity
public class Screen {

    @Id
    @NotNull
    private String scrnId;

    private String scrnNm;

    private String scrnFuncCd;

    private String scrnDesc;

    private String scrnUrl;

    @NotNull
    private String fstRegDttm;

    @NotNull
    private String fstRegrId;

    @NotNull
    private String lstCngDttm;

    @NotNull
    private String lstChgrId;

}
