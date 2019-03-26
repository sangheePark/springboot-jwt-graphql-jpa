package com.novles.system.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 메뉴
 * 
 * @author Moon
 *
 */
@Table(name = "TB_BO_MENU", schema="SRPAYADM")
@Data
@Entity
public class Menu {

    @Id
    @NotNull
    private String menuId;

    @NotNull
    private String menuNm;

    private String hiMenuId;

    @NotNull
    private String scrnId;

    @NotNull
    private String useYn;

    private String menuTypeCd;

    private String menuSrchOrder;

    @NotNull
    private String fstRegDttm;

    @NotNull
    private String fstRegrId;

    @NotNull
    private String lstCngDttm;

    @NotNull
    private String lstChgrId;

}
