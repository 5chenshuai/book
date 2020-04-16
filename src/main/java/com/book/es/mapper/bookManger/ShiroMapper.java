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

}
