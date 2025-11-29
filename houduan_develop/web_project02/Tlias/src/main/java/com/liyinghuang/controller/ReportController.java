package com.liyinghuang.controller;

import com.liyinghuang.pojo.JobOption;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.pojo.StuDegreeRec;
import com.liyinghuang.pojo.StudentCountData;
import com.liyinghuang.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption empJobData = reportService.getEmpJobData();
        return Result.success(empJobData);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计性别");
        List<Map> empGenderData = reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }
//    @GetMapping("/empGenderData")
//    public Result getReportEmpGenderData(){
//        List<Map> r=reportService.getReportEmpGenderData();
//        return Result.success(r);
//    }
    // 5.3 学员学历统计
    @GetMapping("/studentDegreeData")
    public Result getReportStudentDegreeData(){
        List<Map> reportStudentDegreeData = reportService.getReportStudentDegreeData();
        return Result.success(reportStudentDegreeData);
    }
    // 5.4 班级人数统计
    @GetMapping("/studentCountData")
    public Result getReportStudentCountData(){
        StudentCountData reportStudentCountData = reportService.getReportStudentCountData();
        return Result.success(reportStudentCountData);
    }
}
