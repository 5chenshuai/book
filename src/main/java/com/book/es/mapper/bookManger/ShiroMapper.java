package com.book.es.mapper.bookManger;

import com.book.es.bean.Permissions;
import com.book.es.bean.Role;
import com.book.es.bean.User;
import com.book.es.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ShiroMapper {

    User queryUser(String userName);

    Set<Permissions> queryPermissionsByUserName(String userName);

    Integer updateUser(User user);

    Integer addUser(User user);

    Integer addUserRoleById(Integer uid,Integer rid);

    Integer updateUserRole(Integer uid,Integer rid);

    String queryRoleByUid(Integer uid);

    List<UserVO> queryAllUser(UserVO userVO);

}
