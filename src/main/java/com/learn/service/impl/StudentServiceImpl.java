package com.learn.service.impl;

import com.learn.AppRun.StudentMapper;
import com.learn.entity.Student;
import com.learn.service.StudentService;
import com.learn.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student findBySno(int sno) {
        String studentJson = RedisUtil.getVal(String.valueOf(sno));
        if (studentJson != null && studentJson.length() > 0) {
            return JSONObject.parseObject(studentJson, Student.class);
        } else {
            Student student = studentMapper.findBySno(sno);
            RedisUtil.setVal(String.valueOf(sno), JSONObject.toJSONString(student));
            return student;
        }

    }
}
