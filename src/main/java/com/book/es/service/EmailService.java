package com.book.es.service;

import java.util.Date;

public interface EmailService {

    boolean sendEmail(String name, String email, String bookName, Date shouldReturnDay);
}
