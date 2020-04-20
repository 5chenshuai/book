package com.book.es.mapper.bookManger;

import com.book.es.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {


    int addMenu(@Param("menu_name")String menuName, Integer pid);
    List<Menu> selectMenu(List<Integer> pid);


}
