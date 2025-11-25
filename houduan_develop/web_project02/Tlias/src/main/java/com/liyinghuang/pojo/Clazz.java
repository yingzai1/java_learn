package com.liyinghuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    Integer id;
    String name;
    String room;
    LocalDate beginDate;
    LocalDate endDate;
    Integer masterId;
    String masterName;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    String status;
    Integer subject;
}
