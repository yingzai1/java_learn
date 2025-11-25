package com.liyinghuang.controller;

import com.liyinghuang.pojo.Result;
import com.liyinghuang.utils.AliyunOSSOperator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @RequestMapping("/upload")
    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        //1、将文件保存到本地
//        String originalFilename = file.getOriginalFilename();
//        //获取文件的后缀
//        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//        //生成文件名字
//        String string = UUID.randomUUID().toString();
//        //将文件转存到本地
//        file.transferTo(new File("D:/Temp/"+string+substring));
//        return Result.success();
        //2、将文件保存到云端（阿里云）
        String originalFilename = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        String upload = aliyunOSSOperator.upload(bytes, originalFilename);
        return Result.success(upload);
    }
}
