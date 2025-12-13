package com.sky.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
    @PostMapping()
    //用来新增员工
    public Result addEmployee(@RequestBody EmployeeDTO  employeeDTO) {
        log.info("即将录入的员工信息为：{}", employeeDTO);
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }
    /*
    * 完成对员工的分页查询的功能
    * */
    @ApiOperation("完成对员工的分页查询的功能")
    @GetMapping("/page")
    public Result<PageResult> pageEmployee(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("接收到的分页参数为{}", employeePageQueryDTO);
        PageResult res=employeeService.pageEmployee(employeePageQueryDTO);
        return Result.success(res);
    }
    @ApiOperation("开启或禁用员工")
    @PostMapping("/status/{status}")
    public Result startStop(@PathVariable Integer status,Long id){
        log.info("控制层接收到的数据为status={},id={}",status,id);
        employeeService.startStop(status,id);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id来查找员工")
    public Result getEmployeeById(@PathVariable Long id) {
        log.info("根据id来查找员工");
        Employee employee= employeeService.getEmployeeById(id);
        return Result.success(employee);
    }
    @PutMapping()
    public Result updateEmployee(@RequestBody EmployeeDTO  employeeDTO) {
        log.info("根据id来更新员工信息");
        employeeService.updateEmployee(employeeDTO);
        return Result.success();
    }
}
