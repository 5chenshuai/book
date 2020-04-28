package com.book.es.oss;

import com.book.es.enums.ErrorCode;
import com.book.es.enums.FileTypeEnum;
import com.book.es.service.AliyunOSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Service
public class AliyunOSSServiceImpl implements AliyunOSSService {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    @Override
    public List<String> uploadImg(MultipartFile[] files) throws RuntimeException {
        if (files == null || files.length==0) {
            throw new RuntimeException(ErrorCode.OPT_UPLOAD_FILE.getName());
        }
        try {
            return aliyunOSSUtil.uploadFile(files, FileTypeEnum.IMG, aliyunOSSProperties.getBucketApp(), "img", aliyunOSSProperties.getDomainApp());
        } catch (Exception e) {
//            logger.error("uploadImg error e:{}", e);
            throw new RuntimeException(ErrorCode.UPLOAD_FAIL.getName());
        }
    }

    @Override
    public String uploadImg(MultipartFile file) throws RuntimeException {
        MultipartFile[] files = new MultipartFile[1];
        files[0] = file;
        if (file == null) {
            throw new RuntimeException(ErrorCode.OPT_UPLOAD_FILE.getName());
        }
        try {
            return aliyunOSSUtil.uploadFile(files, FileTypeEnum.IMG, aliyunOSSProperties.getBucketApp(), "img", aliyunOSSProperties.getDomainApp()).get(0);
        } catch (Exception e) {
//            logger.error("uploadImg error e:{}", e);
            throw new RuntimeException(ErrorCode.UPLOAD_FAIL.getName());
        }
    }

    @Override
    public List<String> uploadPdf(MultipartFile[] files) {
        return null;
    }

    @Override
    public String uploadPdf(MultipartFile file) {
        MultipartFile[] files = new MultipartFile[1];
        files[0] = file;
        if (file == null) {
            throw new RuntimeException(ErrorCode.OPT_UPLOAD_FILE.getName());
        }
        try {
            return aliyunOSSUtil.uploadFile(files, FileTypeEnum.PDF, aliyunOSSProperties.getBucketApp(), "pdf", aliyunOSSProperties.getDomainApp()).get(0);
        } catch (Exception e) {
//            logger.error("uploadImg error e:{}", e);
            throw new RuntimeException(ErrorCode.UPLOAD_FAIL.getName());
        }
    }
}
