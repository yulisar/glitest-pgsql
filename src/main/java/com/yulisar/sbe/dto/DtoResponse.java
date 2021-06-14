/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yulizar
 */
public class DtoResponse {

    private String status;
    private List data;
    private Map<String, Object> message;

    public void addMessage(String key, Object value) {
        if (this.message == null) {
            this.message = new HashMap<>();
            //this.message = new HashMap<String, Object>();
        }
        this.message.put(key, value);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String stat) {
        this.status = stat;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}
