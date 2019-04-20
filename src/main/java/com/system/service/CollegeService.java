package com.system.service;

import com.system.po.College;
import com.system.po.CollegeCustom;
import com.system.po.StudentCustom;

import java.util.List;


public interface CollegeService {

    List<College> finAll() throws Exception;

    int countTotal();

    List<CollegeCustom> findByPaging(Integer toPageNo) throws Exception;

    //保存院系信息
    Boolean save(CollegeCustom collegeCustom) throws Exception;

    CollegeCustom findById(Integer id) throws Exception;

    //根据id个更新院系信息
    void updataById(Integer id, CollegeCustom collegeCustom) throws Exception;

    //根据id删除院系信息
    void removeById(Integer id) throws Exception;

    //根据名字模糊查询
    List<CollegeCustom> findByName(String name) throws Exception;

}
