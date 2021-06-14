/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.dao;

import com.yulisar.sbe.dto.DtoParamPaging;
import com.yulisar.sbe.vo.VoUser;
import java.util.List;

/**
 *
 * @author Yulizar
 */
public interface UserDao {

    int countUserList(String searchstr);

    int checkUserExist(VoUser voUser);

    VoUser save(VoUser user);

    List<VoUser> getUserList(DtoParamPaging parampage);

    public VoUser delete(int userId);
}
