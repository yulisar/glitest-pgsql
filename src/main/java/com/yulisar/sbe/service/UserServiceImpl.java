/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.service;

import com.yulisar.sbe.dao.UserDao;
import com.yulisar.sbe.dto.DtoParamPaging;
import com.yulisar.sbe.dto.DtoResponseDataTables;
import com.yulisar.sbe.vo.DataTableCriterias;
import com.yulisar.sbe.vo.VoFilter;
import com.yulisar.sbe.vo.VoUser;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yulizar
 */
@Service(value = "userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "userDao")
    private UserDao userDao;

    @Override
    public VoUser saveUser(VoUser voUser) {
//        if (userDao.checkUserExist(voUser) == 0) {
//            userDao.save(voUser);
//            return voUser;
//        }
//        if (voUser.getIduser() == -1) {
            userDao.save(voUser);
            return voUser;
//        } else {
            
//        }
        
//        return new VoUser();
    }

    @Override
    //public List<VoUser> getUserList(VoFilter search, int offset, int length) {
    public List<VoUser> getUserList(DtoParamPaging parampaging) {

        List<VoUser> listVoUser = userDao.getUserList(parampaging);
        return listVoUser;
    }

    @Override
    public DtoResponseDataTables getUserList(DataTableCriterias dtc) {
        int length = dtc.getLength();
        int offset = dtc.getStart();

        //Get the searched string
        Map<String, Object> searchmap = new HashMap<>();
        Set set = dtc.getSearch().entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            String key = (String) me.getKey().toString();
            String val = (String) me.getValue().toString();
            searchmap.put(key, val);
        }
        String searchval = searchmap.get("value").toString();

        //Get the order criteria
        //List<Map<DataTableCriterias.OrderCriterias, String>> setorder = dtc.getOrder();
        int sortedFieldNumber = 0;
        String orderDir = "";
        //Iterator iterator2 = set.iterator();

        List<Map<DataTableCriterias.OrderCriterias, String>> orderdata = dtc.getOrder();
        for (Map<DataTableCriterias.OrderCriterias, String> map_order : orderdata) {
            Set orderSet = map_order.entrySet();
            Iterator iterOrder = orderSet.iterator();
            while (iterOrder.hasNext()) {
                Map.Entry me = (Map.Entry) iterOrder.next();
                if (me.getKey().toString().contains("column")) {
                    sortedFieldNumber = Integer.parseInt((String) me.getValue());
                }
                if (me.getKey().toString().contains("dir")) {
                    orderDir = (String) me.getValue();
                }
            }
        }

        // sorted field column number based on Datatables order. 
        // 0 for NAMA
        // 1 for EMAIL and so on....
        String sortedField = "";
        if (sortedFieldNumber == 0) {
            sortedField = "NAMA";
        } else if (sortedFieldNumber == 1) {
            sortedField = "JENIS_KELAMIN";
        } else if (sortedFieldNumber == 2) {
            sortedField = "TANGGAL_LAHIR";
        } else if (sortedFieldNumber == 3) {
            sortedField = "EMAIL";
        } else if (sortedFieldNumber == 4) {
            sortedField = "ALAMAT";
        } else if (sortedFieldNumber == 5) {
            sortedField = "NAMA_ROLE";
        }

        //Create nice vo param for DAO parameters
        DtoParamPaging dtoParamPaging = new DtoParamPaging();
        dtoParamPaging.setSortCol(sortedField);
        dtoParamPaging.setOrder(orderDir);
        dtoParamPaging.setSearch(searchmap);
        dtoParamPaging.setOffset(offset);
        dtoParamPaging.setLimit(length);

        //crate vo response to accept the result
        DtoResponseDataTables dtoResponse = new DtoResponseDataTables();
        dtoResponse.setData(userDao.getUserList(dtoParamPaging));
        dtoResponse.setRecordsFiltered(userDao.countUserList((String) searchmap.get("value")));
//        vo.setFilter("%");
        dtoResponse.setRecordsTotal(userDao.countUserList(""));
        return dtoResponse;

    }

    @Override
    public String getUserExist() {
        int cnt = userDao.countUserList("");
        if (cnt > 0) {
            return "exist";
        } else {
            return "none";
        }
    }

    @Override
    public VoUser deleteUser(int userId) {
        try {
            VoUser voUser = userDao.delete(userId);
            return voUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        VoUser voUser = new VoUser();
        voUser.setIduser(-1);
        return voUser;
    }
}
