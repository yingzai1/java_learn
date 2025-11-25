package com.liyinghuang.service;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.JobOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportService {
    public JobOption getEmpJobData();
    public List<Map> getEmpGenderData();
}
