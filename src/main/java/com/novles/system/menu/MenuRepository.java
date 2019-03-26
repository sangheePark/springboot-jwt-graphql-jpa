package com.novles.system.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    
    @Query(value="SELECT * FROM" + 
                "    (" + 
                "    SELECT A.*" + 
                "    FROM SRPAYADM.TB_BO_MENU A JOIN SRPAYADM.TB_BO_PRMSMENU B ON A.MENU_ID = B.MENU_ID" + 
                "    WHERE B.PRIV_GRP_ID = :privGrpId" + 
                "    )" + 
                " WHERE USE_YN = 'Y'" + 
                " AND MENU_TYPE_CD IN('D', 'M')" + 
                " AND (HI_MENU_ID = 'SYRUPPAY' OR SCRN_ID IS NOT NULL)" + 
                " START WITH HI_MENU_ID = 'SYRUPPAY' CONNECT BY PRIOR MENU_ID = HI_MENU_ID" 
                , nativeQuery = true)
    List<Menu> findByAuthGroupByid(@Param("privGrpId") String id);
}


