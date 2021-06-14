/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.dao;

import com.oracle.net.Sdp;
import com.yulisar.sbe.dto.DtoParamPaging;
import com.yulisar.sbe.model.User;
import com.yulisar.sbe.model.UserRole;
import com.yulisar.sbe.vo.VoRole;
import com.yulisar.sbe.vo.VoUser;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Yulizar
 */
@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public VoUser save(VoUser voUser) {
        //create Entity to insert the data
        User user = new User();

        user.setNama(voUser.getNama());
        user.setJenisKelamin(voUser.getJenisKelamin());
        //user.setTanggalLahir(voUser.getTanggalLahir());
        user.setAlamat(voUser.getAlamat());
        user.setEmail(voUser.getEmail());

//        User user = new User();
        Criteria critrole = sessionFactory.getCurrentSession().createCriteria(UserRole.class);
        critrole.add(Restrictions.eq("namaRole", voUser.getNamaRole()));

        UserRole userRole = new UserRole();
        List<UserRole> resultsuserrole = critrole.list();
        for (UserRole userRoleitem : resultsuserrole) {
            userRole.setIdRole(userRoleitem.getIdRole());
            userRole.setNamaRole(userRoleitem.getNamaRole());
        }
        user.setUserRole(userRole);

        //Date dob = null;
        String pattern = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date dob = sdf.parse(voUser.getTanggalLahir());
            user.setTanggalLahir(dob);
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Criteria crituser = sessionFactory.getCurrentSession().createCriteria(User.class);
//        crituser.add(Restrictions.eq("idUser", voUser.getIduser()));
//        List<User> resultsuser = crituser.list();
        //save to DB if id and role availalble 
//        if (resultsuser.size() == 0) {
        //sessionFactory.getCurrentSession().save(user);
        if (voUser.getIduser() > 0) {
            user.setIdUser(voUser.getIduser());
            sessionFactory.getCurrentSession().update(user);
        } else {
            sessionFactory.getCurrentSession().save(user);
        }

        return voUser;
//        }
//        return new VoUser();
    }

    @Override
    public List<VoUser> getUserList(DtoParamPaging parampage) {

        String searchstr = (String) parampage.getSearch().get("value");
        StringBuilder sqlstrsb = new StringBuilder(queryUserEmail());
        searchstr = "%" + searchstr + "%";
        appendSqlQueryBySearch(sqlstrsb, searchstr);
        appendSort(sqlstrsb, parampage);
        appendLimit(sqlstrsb, parampage.getOffset(), parampage.getLimit());
        SQLQuery q = sessionFactory.getCurrentSession()
                .createSQLQuery(sqlstrsb.toString());
        List<VoUser> listVoUser = new ArrayList<>();
        List<Object> lobj = new ArrayList<>();
        if (searchstr != null && !searchstr.isEmpty()) {
            q.setParameter("filter", searchstr);
        }
        lobj = q.list();
        for (Object obj : lobj) {
            VoUser vo = new VoUser();
            Object[] arrobj = (Object[]) obj;
            vo.setIduser((int) arrobj[0]);
            vo.setNama((String) arrobj[1]);
            StringBuffer sbdob = new StringBuffer();

            if (arrobj[2] != null) {
                String pattern = "dd-MMM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                String strdob = sdf.format(arrobj[2]);
                sbdob.append(strdob);
            }

            vo.setTanggalLahir(sbdob.toString());
            vo.setJenisKelamin((char) arrobj[3]);
            vo.setEmail((String) arrobj[4]);
            vo.setAlamat((String) arrobj[5]);
            vo.setIdRole((int) arrobj[6]);
            vo.setNamaRole((String) arrobj[7]);
            listVoUser.add(vo);
        }
        return listVoUser;
    }

    @Override
    public int countUserList(String searchstr) {
        StringBuilder sqlstrsb = new StringBuilder(queryCountUserEmail());
        searchstr = "%" + searchstr + "%";
        appendSqlQueryBySearch(sqlstrsb, searchstr);
        SQLQuery q = sessionFactory.getCurrentSession()
                .createSQLQuery(sqlstrsb.toString());
        if (searchstr != null && !searchstr.isEmpty()) {
            q.setParameter("filter", searchstr);
        }
        BigInteger total = (BigInteger) q.uniqueResult();
        return total.intValue();
    }

    String queryUserEmail() {
        return "select tu.ID, NAMA, TANGGAL_LAHIR, JENIS_KELAMIN, EMAIL, ALAMAT, "
                + "ROLE_ID , tr.NAMA_ROLE as NAMA_ROLE \n"
                + getFromClause();
    }

    String queryCountUserEmail() {
        return "select count(1) " + getFromClause();
    }

    String getFromClause() {
        return " FROM tb_master_user tu, tb_master_user_role tr  "
                + "WHERE tu.ROLE_ID = tr.ID "
                + "AND 1=1 ";
    }

    private void appendSqlQueryBySearch(StringBuilder sql, String filters) {
        String filter = filters;
        if (filters != null && !filter.isEmpty()) {
            if (StringUtils.isNotBlank(filter)) {

                sql.append("AND (upper(NAMA) LIKE  upper(:filter) ");
                sql.append("OR upper(tr.NAMA_ROLE) LIKE  upper(:filter) ");
                sql.append("OR upper(JENIS_KELAMIN) LIKE  upper(:filter) ");
                sql.append("OR upper(EMAIL) LIKE  upper(:filter) ");
                sql.append("OR upper(ALAMAT) LIKE  upper(:filter)) ");
                
//                
//                sql.append("OR tr.NAMA_ROLE LIKE  concat('%', upper(:filter), '%') ");
//                sql.append("OR JENIS_KELAMIN LIKE  concat('%', upper(:filter), '%') ");
//                sql.append("OR EMAIL LIKE  concat('%', upper(:filter), '%')) ");

//                sql.append("AND upper(NAMA) like upper(:filter) ");
//                sql.append("AND upper(ALAMAT) like upper(:filter) ");
//                sql.append("AND upper(NAMA) like upper(:filter) ");
//                sql.append("AND upper(NAMA) like upper(:filter) ");
//                sql.append("AND upper(NAMA) like upper(:filter) ");
//                sql.append("AND upper(NAMA) like upper('%:filter%') ");
//                sql.append("AND (upper(NAMA) LIKE  '%', upper(:filter), '%') ");
//                sql.append("OR ALAMAT LIKE  concat('%', upper(:filter), '%') ");
//                sql.append("OR tr.NAMA_ROLE LIKE  concat('%', upper(:filter), '%') ");
//                sql.append("OR JENIS_KELAMIN LIKE  concat('%', upper(:filter), '%') ");
//                sql.append("OR EMAIL LIKE  concat('%', upper(:filter), '%')) ");
            }
        }
    }

    //upper(NAMA) like upper('%i%')
    private void appendSort(StringBuilder sb, DtoParamPaging parampage) {
        String sortcol = parampage.getSortCol();
        String sortoder = parampage.getOrder();
        sb.append(" ORDER BY ").append(sortcol).append(" ").append(sortoder);
    }

    private void appendLimit(StringBuilder sql, int offset, int length) {
        if (length > 0) {
            sql.append(" LIMIT ").append(length).append(" OFFSET ").append(offset);  //postgre
//            sql.append(" LIMIT ").append(offset).append(", ").append(length); //mysql
        }

    }

    @Override
    public int checkUserExist(VoUser voUser) {
        //while all user returned
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(User.class, "user");
        criteria.add(Restrictions.eq("idUser", voUser.getIduser()));
        List<User> res = criteria.list();
        return res.size();

    }

    @Override
    public VoUser delete(int userId) {
        Criteria critUser = sessionFactory.getCurrentSession().createCriteria(User.class);
        critUser.add(Restrictions.eq("idUser", userId));

        List<User> listUser = critUser.list();
        User user = (User) critUser.uniqueResult();
//        for (User useritem : listUser) {
//            User user = new User();
//            user = useritem;
//            
//        }
        sessionFactory.getCurrentSession().delete(user);

        VoUser voUser = new VoUser();
        voUser.setIduser(userId);

        return voUser;
    }

}
