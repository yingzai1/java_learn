package com.liyinghuang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmpController {

}
//查询出所有员工的信息及其所属部门的名称
//select emp.* , dept.name from emp left inner dept on emp.dept_id=dept.id