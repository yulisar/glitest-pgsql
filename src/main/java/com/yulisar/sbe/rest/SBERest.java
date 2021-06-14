/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.rest;

/**
 *
 * @author Yulizar
 */
import com.yulisar.sbe.dto.DtoResponseDataTables;
import com.yulisar.sbe.service.UserService;
import com.yulisar.sbe.vo.DataTableCriterias;
import com.yulisar.sbe.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class SBERest {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    @RequestMapping(value = "get-user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponseDataTables getUserList(DataTableCriterias dtc) {
        return userService.getUserList(dtc);
    }

    @RequestMapping(value = "get-user-exist",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    String getUserExist() {
        return userService.getUserExist();
    }

    @RequestMapping(value = "save-user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    VoUser saveUser(
            @RequestBody VoUser voUser) {
        return userService.saveUser(voUser);
    }

    @RequestMapping(value = "delete-user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    VoUser deleteUser(
            @RequestBody VoUser voUser) {
        int idUser = voUser.getIduser();
        return userService.deleteUser(idUser);
    }

}
