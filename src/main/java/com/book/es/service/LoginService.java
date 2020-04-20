package com.book.es.service;

import com.book.es.bean.Role;
import com.book.es.bean.User;

import java.util.Set;

public interface LoginService {

    User getUserByName(String getMapByName);

    Boolean updatePasswordById(String password,Integer id);

    boolean addUser(User user);

    boolean addUserRole(Integer uid,Integer rid);

    boolean updateUserRoleById(Integer uid, Integer rid);



}
