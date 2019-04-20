package com.system.service.impl;

import com.system.mapper.CollegeMapper;
import com.system.mapper.CollegeMapperCustom;
import com.system.po.*;
import com.system.service.CollegeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CollegeMapperCustom collegeMapperCustom;

    @Override
    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }

    @Override
    public int countTotal() {
        //自定义查询对象
        CollegeExample studentExample = new CollegeExample();
        //通过criteria构造查询条件
        CollegeExample.Criteria criteria = studentExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.countByExample(studentExample);
    }

    @Override
    public List<CollegeCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        return collegeMapperCustom.findByPaging(pagingVO);
    }

    @Override
    public Boolean save(CollegeCustom collegeCustom) throws Exception {
        College college = collegeMapper.selectByPrimaryKey(collegeCustom.getCollegeid());
        if (college == null) {
            collegeMapper.insert(collegeCustom);
            return true;
        }
        return false;
    }

    @Override
    public CollegeCustom findById(Integer id) throws Exception {
        College college  = collegeMapper.selectByPrimaryKey(id);
        CollegeCustom collegeCustom = null;
        if (college != null) {
            collegeCustom = new CollegeCustom();
            //类拷贝
            BeanUtils.copyProperties(college, collegeCustom);
        }
        return collegeCustom;
    }

    @Override
    public void updataById(Integer id, CollegeCustom collegeCustom) throws Exception {
        collegeMapper.updateByPrimaryKey(collegeCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        collegeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CollegeCustom> findByName(String name) throws Exception {
        CollegeExample studentExample = new CollegeExample();
        //自定义查询条件
        CollegeExample.Criteria criteria = studentExample.createCriteria();
        criteria.andCollegenameLike("%" + name + "%");
        List<College> list = collegeMapper.selectByExample(studentExample);
        List<CollegeCustom> studentCustomList = null;
        if (list != null) {
            studentCustomList = new ArrayList<CollegeCustom>();
            for (College s : list) {
                CollegeCustom collegeCustom = new CollegeCustom();
                //类拷贝
                BeanUtils.copyProperties(s, collegeCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                collegeCustom.setCollegename(college.getCollegename());
                studentCustomList.add(collegeCustom);
            }
        }
        return studentCustomList;
    }
}
