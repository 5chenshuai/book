package com.book.es.controller;

import com.book.es.annotation.ValidatorAnnotation;
import com.book.es.bean.Role;
import com.book.es.bean.User;
import com.book.es.logging.Logging;
import com.book.es.service.LoginService;
import com.book.es.vo.UserVO;
import com.book.es.web.BaseController;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class LoginController extends Logging implements BaseController {

    private Logger loginLogger = LogManager
            .getLogger("login");

    @Autowired
    private LoginService loginService;

//    @ValidatorAnnotation
    @PostMapping("/login")
    public WebResponse login(@RequestBody @Valid User user, BindingResult bindingResult) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        UserVO userInfo;
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            infoWithLogger(loginLogger,"登陆成功uId:<{}>",user.getUserName());
            userInfo = loginService.getUserInfo(user.getUserName());
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            return defaultErr().setMsg("账号或密码错误！");
        } catch (AuthorizationException e) {
            return defaultErr().setMsg("没有权限");
        }
        return ok().setData(userInfo);
    }


    @PostMapping("/user/update")
    public WebResponse updatePassword(@RequestBody User user){
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        user.setId(user1.getId());
        if(loginService.updateUser(user)) {
            return ok().setData(loginService.getUserInfo(user.getUserName()));
        } else {
            return defaultErr();
        }
    }

    @RequiresPermissions("user/add")
    @PostMapping("/user/add")
    public WebResponse addUser(@RequestBody User user){
        loginService.addUser(user);
        loginService.addUserRole(user.getId(),3);
        return ok();
    }

    @RequiresPermissions("user/update")
    @PostMapping("/user/updaterole")
    public WebResponse updateUserRole(Integer uid,Integer rid){
        loginService.updateUserRoleById(uid,rid);
        return ok();
    }

//    @RequiresPermissions("user/get")
//    @GetMapping("/user/get")
//    public WebResponse getUserInfo(String userName) {
//        return ok().setData(loginService.getUserInfo(userName));
//    }


    @RequiresPermissions("user/get")
    @GetMapping("/user/getAll")
    public WebResponse getAllUserInfo(UserVO userVO,@PageableDefault(page = 0,size = 10) Pageable pageable) {
        if(userVO.getUserName()!=null) {
            UserVO userInfo = loginService.getUserInfo(userVO.getUserName());
            List<UserVO> userVOS = new ArrayList<>();
            userVOS.add(userInfo);
            new PageResult(userVOS,1, (long) 1,0,10,true,true);
            return ok().setData(userVOS);
        } else {
            return ok().setData(loginService.queryAllUser(userVO,pageable));
        }
    }

    @GetMapping("/index")
    public WebResponse index(){
        return ok();
    }
}
