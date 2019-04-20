package com.system.mapper;

import com.system.po.CollegeCustom;
import com.system.po.PagingVO;

import java.util.List;

public interface CollegeMapperCustom {

    List<CollegeCustom> findByPaging(PagingVO pagingVO) throws Exception;

}