package com.novles.system.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
    
    public Optional<Menu> findById(String id) {
        return menuRepository.findById(id);
    }
    
    public List<Menu > findByAuthGroupId(String id) {
        return menuRepository.findByAuthGroupByid(id);
    }
   
}
