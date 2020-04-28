package com.book.es.impl;

import com.book.es.bean.Menu;
import com.book.es.mapper.bookManger.MenuMapper;
import com.book.es.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Integer> selectPidByRole(Integer roleId) {
        return menuMapper.selectPidByRole(roleId);
    }

    @Override
    public List<Menu> selectMenu(List<Integer> pid) {
        return menuMapper.selectMenu(pid);
    }
}
