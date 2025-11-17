package com.liyinghuang.controller;

import com.liyinghuang.pojo.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    @RequestMapping("/upload")
    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成文件名字
        String string = UUID.randomUUID().toString();
        //将文件转存到本地
        file.transferTo(new File("D:/Temp/"+string+substring));
        return Result.success();
    }
}
