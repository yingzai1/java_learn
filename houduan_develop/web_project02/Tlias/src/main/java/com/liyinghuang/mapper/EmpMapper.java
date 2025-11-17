package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;


//无条件分页查询方法一：
////对员工信息进行操作的结构
//@Mapper
//public interface EmpMapper {
//    @Select("select count(*) from emp")
//    public Long count();
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
//            " order by e.update_time limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);
//}

//无条件分页查询方法二：
////对员工信息进行操作的结构
//@Mapper
//public interface EmpMapper {
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
//            " order by e.update_time")
//    public List<Emp> list();
//}
@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp")
    public Long count();
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
    //        " order by e.update_time limit #{start},#{pageSize}")
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "VALUES(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void add_emp(Emp emp);
}