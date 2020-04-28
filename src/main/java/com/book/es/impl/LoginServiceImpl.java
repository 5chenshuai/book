package com.book.es.impl;

import com.book.es.bean.Permissions;
import com.book.es.bean.Role;
import com.book.es.bean.User;
import com.book.es.mapper.bookManger.ShiroMapper;
import com.book.es.service.LoginService;
import com.book.es.vo.UserVO;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ShiroMapper shiroMapper;

    @Override
    public User getUserByName(String getMapByName) {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(getMapByName);
    }

    @Override
    public Boolean updateUser(User user) {

        shiroMapper.updateUser(user);

        return true;
    }

    @Override
    public boolean addUser(User user) {
        return shiroMapper.addUser(user)==1;
    }

    @Override
    public boolean addUserRole(Integer uid, Integer rid) {
        shiroMapper.addUserRoleById(uid,rid);
        return true;
    }

    @Override
    public boolean updateUserRoleById(Integer uid,Integer rid) {
        shiroMapper.updateUserRole(uid,rid);
        return true;
    }

    @Override
    public PageResult<UserVO> queryAllUser(UserVO userVO,Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        com.github.pagehelper.Page<UserVO> users = PageHelper.startPage(pageNumber, pageSize);
        List<UserVO> userVOS = shiroMapper.queryAllUser(userVO);
        PageInfo<UserVO> borrowPageInfo = new PageInfo<>(users);
        long totalElements = borrowPageInfo.getTotal();//总数据条数
        int number = borrowPageInfo.getPageNum();//当前页
        int size = borrowPageInfo.getPageSize();//当前页数据条数
        int totalPages = borrowPageInfo.getPages();//总页数
        boolean first = borrowPageInfo.isIsFirstPage();
        boolean last = borrowPageInfo.isIsLastPage();
        List<UserVO> content = borrowPageInfo.getList();//结果集8
        return new PageResult(content,totalPages,totalElements,number,size,first,last);
    }

    @Override
    public UserVO getUserInfo(String userName) {
        User user = shiroMapper.queryUser(userName);
        String role = shiroMapper.queryRoleByUid(user.getId());
        return new UserVO(user.getId(),user.getUserName(),role,user.getName(),user.getEmail());
    }


    /**
     * 模拟数据库查询
     * @param userName
     * @return
     */
    private User getMapByName(String userName){

        User user = shiroMapper.queryUser(userName);
        Set<Permissions> permissions = shiroMapper.queryPermissionsByUserName(userName);
        Role role = new Role();
        role.setPermissions(permissions);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
//        Permissions permissions1 = new Permissions(1,"query");
//        Permissions permissions2 = new Permissions(1,"add");
//        Set<Permissions> permissionsSet = new HashSet<>();
//        permissionsSet.add(permissions1);
//        permissionsSet.add(permissions2);
//        Role role = new Role(1,"admin",permissionsSet);
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        User user = new User(1,"wsl","123456",roleSet);
//        Map<String ,User> map = new HashMap<>();
//        map.put(user.getUserName(), user);
//
//        Permissions permissions3 = new Permissions(3,"query");
//        Set<Permissions> permissionsSet1 = new HashSet<>();
//        permissionsSet1.add(permissions3);
//        Role role1 = new Role(2,"user",permissionsSet1);
//        Set<Role> roleSet1 = new HashSet<>();
//        roleSet1.add(role1);
//        User user1 = new User(2,"zhangsan","123456",roleSet1);
//        map.put(user1.getUserName(), user1);
//        return map.get(userName);
        return user;
    }
}
