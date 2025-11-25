package com.liyinghuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageWrap<T> {
    private List<T> rows;  // 原 data 数组
    private long total;    // 总条数
}
