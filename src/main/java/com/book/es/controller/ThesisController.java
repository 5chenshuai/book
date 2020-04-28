package com.book.es.controller;

import com.book.es.bean.Book;
import com.book.es.bean.Thesis;
import com.book.es.enums.BookStatusEnum;
import com.book.es.service.AliyunOSSService;
import com.book.es.service.ThesisService;
import com.book.es.web.BaseController;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/thesis")
public class ThesisController implements BaseController {

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private AliyunOSSService aliyunOSSService;

    @RequiresPermissions("thesis/add")
    @PostMapping("/add")
    public WebResponse addThesis(@RequestBody Thesis thesis) {
        try {
            if(thesisService.addThesis(thesis)) {
                return ok();
            } else {
                return defaultErr();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultErr().setMsg(e.getMessage());
        }
    }

    @RequiresPermissions("thesis/add")
    @PostMapping("/uploadPdf")
    public WebResponse uploadPdf( @RequestParam(value = "file",required = false) MultipartFile file) {
        return ok().setData(aliyunOSSService.uploadPdf(file));
    }

    @RequiresPermissions("thesis/delete")
    @PostMapping("/delete")
    public WebResponse deleteThesis(@RequestBody Thesis thesis) {
        if(thesisService.deleteThesis(thesis)) {
            return ok();
        }else {
            return defaultErr();
        }
    }

    @GetMapping("/guest/search")
    public WebResponse queryThesis(Thesis thesis,@PageableDefault(page = 0,size = 10) Pageable pageable) {
        Page<Thesis> theses = thesisService.queryThesis(thesis,pageable);
        return ok().setData(new PageResult<Thesis>(theses.getContent(),theses.getTotalPages(),theses.getTotalElements()
                ,theses.getNumber(),theses.getSize(),theses.isFirst(),theses.isLast()));
    }


}
