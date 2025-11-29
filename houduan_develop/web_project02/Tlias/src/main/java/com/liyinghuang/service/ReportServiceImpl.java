package com.liyinghuang.service;

import com.liyinghuang.mapper.EmpMapper;
import com.liyinghuang.pojo.JobOption;
import com.liyinghuang.pojo.StuDegreeRec;
import com.liyinghuang.pojo.StudentCountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> maps = empMapper.countEmpJobData();
        List<Object> pos = maps.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> nums = maps.stream().map(dataMap -> dataMap.get("nums")).toList();
        return new JobOption(pos,nums);
    }

    @Override
    public List<Map> getEmpGenderData() {
        List<Map> maps = empMapper.countEmpGenderData();
        return maps;
    }

    @Override
    public List<Map> getReportEmpGenderData() {
        return empMapper.getReportEmpGenderData();
    }

    @Override
    public List<Map> getReportStudentDegreeData() {
        List<Map> reportStudentDegreeData = empMapper.getReportStudentDegreeData();
//        List<Object> name = reportStudentDegreeData.stream().map(dataMap -> dataMap.get("jobList")).toList();
//        List<Object> dataList = reportStudentDegreeData.stream().map(dataMap -> dataMap.get("dataList")).toList();
        return reportStudentDegreeData;

    }

    @Override
    public StudentCountData getReportStudentCountData() {
        List<Map> reportStudentCountData = empMapper.getReportStudentCountData();
        List<Object> clazz = reportStudentCountData.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> value = reportStudentCountData.stream().map(dataMap -> dataMap.get("value")).toList();
        return new StudentCountData(clazz,value);
    }


}
