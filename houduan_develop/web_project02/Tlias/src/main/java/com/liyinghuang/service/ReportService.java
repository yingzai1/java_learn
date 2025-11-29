package com.liyinghuang.service;

import com.liyinghuang.pojo.JobOption;
import com.liyinghuang.pojo.StuDegreeRec;
import com.liyinghuang.pojo.StudentCountData;

import java.util.List;
import java.util.Map;

public interface ReportService {
    public JobOption getEmpJobData();
    public List<Map> getEmpGenderData();

    List<Map> getReportEmpGenderData();

    List<Map> getReportStudentDegreeData();

    StudentCountData getReportStudentCountData();
}
