/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.service;

import com.yulisar.sbe.dto.DtoParamPaging;
import com.yulisar.sbe.dto.DtoResponseDataTables;
import com.yulisar.sbe.dto.DtoResponsePaging;
import com.yulisar.sbe.vo.DataTableCriterias;
import com.yulisar.sbe.vo.VoFilter;
import com.yulisar.sbe.vo.VoUser;
import java.util.List;

/**
 *
 * @author Yulizar
 */
public interface UserService {
    List<VoUser> getUserList(DtoParamPaging dtop);
    DtoResponseDataTables getUserList (DataTableCriterias dtc);
    VoUser saveUser(VoUser voUser);
    public String getUserExist();

    public VoUser deleteUser(int userId);

}

