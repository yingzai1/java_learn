package com.liyinghuang.service;

import com.liyinghuang.pojo.Clazz;
import com.liyinghuang.pojo.ClazzFindByIdResult;
import com.liyinghuang.pojo.ClazzResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    public ClazzResult selectClazzByCase(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
    void deleteClazzById(Integer id);

    void insertClazz(Clazz clazz);

    ClazzFindByIdResult selectClazzById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getAllClazz();
}
