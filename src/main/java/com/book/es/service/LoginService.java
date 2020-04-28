package com.book.es.service;

import com.book.es.bean.Role;
import com.book.es.bean.User;
import com.book.es.vo.UserVO;
import com.book.es.web.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface LoginService {

    UserVO getUserInfo(String userName);

    User getUserByName(String getMapByName);

    Boolean updateUser(User user);

    boolean addUser(User user);

    boolean addUserRole(Integer uid,Integer rid);

    boolean updateUserRoleById(Integer uid, Integer rid);

    PageResult<UserVO> queryAllUser(UserVO userVO,Pageable pageable);

}
