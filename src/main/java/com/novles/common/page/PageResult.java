package com.novles.common.page;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class PageResult<T> {

    private int page;
    private int size;
    private int totalPages;
    private Long totalElements;
    private List<T> content;

    public PageResult(Long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public PageResult(Long totalElements, List<T> content, PageSearch<T> pageSearch) {
        this.page = pageSearch.getPage();
        this.size = pageSearch.getSize();
        this.totalPages = (int) Math.ceil(totalElements / pageSearch.getSize());
        this.totalElements = totalElements;
        this.content = content;
    }

    public PageResult(Page<T> page) { 
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.content = page.getContent();
    }

    public int getCount() {
        return Optional.ofNullable(this.content).orElse(Lists.newArrayList()).size();
    }
}
