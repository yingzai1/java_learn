package com.liyinghuang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liyinghuang.mapper.ClazzMapper;
import com.liyinghuang.pojo.Clazz;
import com.liyinghuang.pojo.ClazzFindByIdResult;
import com.liyinghuang.pojo.ClazzResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ClazzServiceImp implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public ClazzResult selectClazzByCase(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Clazz> clazzes = clazzMapper.selectClazzByCase(name, begin, end);
        clazzes.forEach(clazz->{clazz.setStatus("已结课");});
        Page<Clazz> clazzes1 = (Page<Clazz>) clazzes;
        ClazzResult clazzResult = new ClazzResult();
        clazzResult.setRows(clazzes1.getResult());
        clazzResult.setTotals(clazzMapper.selectCountByCase(name, begin, end));
        return clazzResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteClazzById(Integer id) {
        clazzMapper.deleteClazzById(id);
    }

    @Override
    public void insertClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());
        clazzMapper.insertClazz(clazz);
    }

    @Override
    public ClazzFindByIdResult selectClazzById(Integer id) {
        return clazzMapper.selectClazzById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        //更新修改时间
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> getAllClazz() {
        return clazzMapper.getAllClazz();
    }


}
