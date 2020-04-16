package com.book.es.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AliyunOSSService {

    List<String> uploadImg(MultipartFile[] files) throws RuntimeException;

    String uploadImg(MultipartFile file) throws RuntimeException;

    List<String> uploadPdf(MultipartFile[] files);

    String uploadPdf(MultipartFile file);

}
