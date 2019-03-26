package com.novles.common.page;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageSearch<T> {

    private Integer page;
    private Integer size;

    private String sort;
    private String sortColumn;
    private String sortType;

    private T param;

    public int getPage() {
        return Optional.ofNullable(this.page).orElse(1);
    }

    public int getSize() {
        return Optional.ofNullable(this.size).orElse(10);
    }

    public int getStart() {
        return (this.getPage() - 1) * this.getSize();
    }

    public int getEnd() {
        return this.getStart() + this.getSize();
    }

    public String getSortColumn() {
        if (!StringUtils.isEmpty(this.sort)) {
            this.sortColumn = this.sort.split("\\.")[0];
        }
        return this.sortColumn;
    }

    public String getSortType() {
        if (!StringUtils.isEmpty(this.sort)) {
            this.sortType = this.sort.split("\\.")[1];
        }
        return this.sortType;
    }

    public PageRequest buildPageRequest() {
        if (!StringUtils.isEmpty(this.sort)) {
            Sort sort = new Sort(Direction.ASC, this.getSortColumn());
            if (this.getSortType().equals("DESC")) {
                sort = new Sort(Direction.DESC, this.getSortColumn());
            }
            return PageRequest.of(this.getPage() - 1, this.getSize(), sort);
        } else {
            return PageRequest.of(this.getPage() - 1, this.getSize());
        }
    }
    
    public PageSearch( Integer page, Integer size, String sort, String sortColumn, String sortType, T param){
        this.page = page;
        this.size = size;
        this.sortColumn = sortColumn;
        this.sortType = sortType;
        this.param = param;
    }
}
