package com.liyinghuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {
    //这里的属性名别写错，不然没法跟前端匹配
    private List jobList;
    private List dataList;
    //private List jobList; 可以在运行时存放任何类型的对象。只不过你失去了编译期的类型检查。
}
