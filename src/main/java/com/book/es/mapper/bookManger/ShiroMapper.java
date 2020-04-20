package com.book.es.mapper.bookManger;

import com.book.es.bean.Permissions;
import com.book.es.bean.Role;
import com.book.es.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface ShiroMapper {

    User queryUser(String userName);

    Set<Permissions> queryPermissionsByUserName(String userName);

    Integer updatePassword(String password,Integer id);

    Integer addUser(User user);

    Integer addUserRoleById(Integer uid,Integer rid);

    Integer updateUserRole(Integer uid,Integer rid);

}
