package com.liyinghuang.service;

import com.liyinghuang.mapper.EmpMapper;
import com.liyinghuang.pojo.JobOption;
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
}
