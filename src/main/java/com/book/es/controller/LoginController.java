package com.book.es.controller;

import com.book.es.annotation.ValidatorAnnotation;
import com.book.es.bean.User;
import com.book.es.logging.Logging;
import com.book.es.web.BaseController;
import com.book.es.web.WebResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController extends Logging implements BaseController {

    private Logger loginLogger = LogManager
            .getLogger("login");

//    @ValidatorAnnotation
    @RequestMapping("/login")
    public WebResponse login(@Valid User user, BindingResult bindingResult) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            infoWithLogger(loginLogger,"登陆成功uId:<{}>",user.getUserName());
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            return defaultErr().setMsg("账号或密码错误！");
        } catch (AuthorizationException e) {
            return defaultErr().setMsg("没有权限");
        }
        return ok();
    }
    //注解验角色和权限
//    @RequiresRoles("admin")
//    @RequiresPermissions("delete1")
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }


    @GetMapping("/book/guest/index1")
    public String index1() {
        return "index!";
    }

    @RequiresPermissions("delete1")
    @PostMapping("/index2")
    public String index2() {
        return "index!";
    }
}
