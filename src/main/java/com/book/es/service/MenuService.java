package com.book.es.service;

import com.book.es.bean.Menu;

import java.util.List;

public interface MenuService {

    List<Integer> selectPidByRole(Integer roleId);

    List<Menu> selectMenu(List<Integer> pid);

}
