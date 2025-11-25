package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Clazz;
import com.liyinghuang.pojo.ClazzFindByIdResult;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
@Mapper
public interface ClazzMapper {
    public List<Clazz> selectClazzByCase(String name, LocalDate begin,LocalDate end);
    public Integer selectCountByCase(String name, LocalDate begin,LocalDate end);
    @Delete("delete from clazz where id=#{id1}")
    void deleteClazzById(Integer id1);
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId}," +
            "#{subject},#{createTime},#{createTime})")
    void insertClazz(Clazz clazz);
    @Select("select * from clazz where clazz.id=#{id}")
    ClazzFindByIdResult selectClazzById(Integer id);
    void updateClazz(Clazz clazz);
    List<Clazz> getAllClazz();
}
