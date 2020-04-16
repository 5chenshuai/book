package com.book.es;

import com.book.es.bean.Book;
import com.book.es.mapper.bookManger.BorrowMapper;
import com.book.es.mapper.bookManger.ShiroMapper;
import com.book.es.mapper.mycat.BookMapper;
import com.book.es.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class EsApplicationTests {

    @Autowired
    private ShiroMapper shiroMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Test
    void contextLoads() {
        System.out.println(shiroMapper.queryUser("admin"));
    }

    @Test
    void contextLoads1() {
        System.out.println(new Date());

//        Book book = new Book();
//        book.setId(20018);
//        book.setStatus(0);
//        try {
//            bookService.updateById(book,null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        book.setBookName("老人与海");
//        book.setAuthor("海明威");
//        book.setFloor("3");
//        book.setBookshelf("3-1");
//        book.setBookNumber("1234");
//        book.setPress("人民出版社");
//        System.out.println(bookService.add(book));

//        System.out.println(bookMapper.selectBookByNumber("123445"));

//        System.out.println(borrowMapper.queryBorrow(1,"1111","红楼梦"));

    }

}
