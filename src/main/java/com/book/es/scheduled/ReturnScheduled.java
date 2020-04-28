package com.book.es.scheduled;


import com.book.es.bean.Borrow;
import com.book.es.bean.BorrowUser;
import com.book.es.impl.EmailImpl;
import com.book.es.service.BorrowService;
import com.book.es.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@EnableAsync
public class ReturnScheduled {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "01 00 00 * * ?")
    void scheduledSend(){
        List<BorrowUser> borrows = borrowService.queryShouldReturnBorrow();

        for (BorrowUser borrowUser:borrows) {
            emailService.sendEmail(borrowUser.getName(),borrowUser.getEmail(),borrowUser.getBookName(),borrowUser.getShouldReturnDay());
        }
        borrowService.updateBorrowOverdue();
    }
}
