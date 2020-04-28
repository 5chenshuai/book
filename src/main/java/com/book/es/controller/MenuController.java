package com.book.es.controller;

import com.book.es.bean.Menu;
import com.book.es.bean.Permissions;
import com.book.es.bean.User;
import com.book.es.enums.RoleEnum;
import com.book.es.mapper.bookManger.ShiroMapper;
import com.book.es.service.MenuService;
import com.book.es.web.BaseController;
import com.book.es.web.WebResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class MenuController implements BaseController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/guest/menu/get")
    public WebResponse selectMenu() {
        List<Integer> pids= new ArrayList<>();;
        if(SecurityUtils.getSubject().getPrincipal()==null) {
            pids = menuService.selectPidByRole(RoleEnum.BOOK_GUEST.getCode());
        } else {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Iterator<Permissions> iterator = user.getRoles().iterator().next().getPermissions().iterator();
            while (iterator.hasNext()) {
                pids.add(iterator.next().getId());
            }
        }
        return ok().setData(menuService.selectMenu(pids));
    }


}
