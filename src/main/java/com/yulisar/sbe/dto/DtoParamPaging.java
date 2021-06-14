/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.dto;

import java.util.Map;

/**
 *
 * @author Yulizar
 */
public class DtoParamPaging {

    private int offset;
    private int limit;
    // field yang akan diurutkan
    private String sortCol;

    //descending atau ascending
    private String order;
    private Map<String, Object> search;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<String, Object> getSearch() {
        return search;
    }

    public void setSearch(Map<String, Object> search) {
        this.search = search;
    }

    public String getSortCol() {
        return sortCol;
    }

    public void setSortCol(String sortCol) {
        this.sortCol = sortCol;
    }
    
    

}
