package com.biz.redisdemo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.biz.redisdemo.dao.IStudentDao;
import com.biz.redisdemo.entity.Student;
import com.biz.redisdemo.pojo.PageRequestVo;
import com.biz.redisdemo.util.RedisConnectionUtils;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @projectName: redis-demo
 * @className: StudentDaoImpl
 * @description: dao 对redis数据操作
 * @author: xy
 * @time: 2021/5/7 14:55
 */
@Repository
public class StudentDaoImpl implements IStudentDao {

    @Override
    public boolean insertStudent(Student student) {
        Jedis jedis = RedisConnectionUtils.getJedis();
        try {
            jedis.zadd("STUDENT_SET", student.getAvgScore(), JSON.toJSONString(student));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<Student> selectByPage(PageRequestVo pageRequestVo) {
        Jedis jedis = RedisConnectionUtils.getJedis();
        Integer page = pageRequestVo.getPage();
        Integer limit = pageRequestVo.getLimit();
        int start = (page - 1) * limit;
        int end = page * limit;
        Set<Tuple> studentSet = jedis.zrevrangeWithScores("STUDENT_SET", start, end);
        ArrayList<Student> studentList = new ArrayList<>();
        studentSet.forEach(tuple -> {
            studentList.add(JSON.parseObject(tuple.getElement(), Student.class));
        });
        jedis.close();
        return studentList;
    }

    @Override
    public Integer count() {
        Jedis jedis = RedisConnectionUtils.getJedis();
        int count = Math.toIntExact(jedis.zcard("STUDENT_SET"));
        jedis.close();
        return count;
    }

    @Override
    public boolean deleteStudent(Student studentOld) {
        Jedis jedis = RedisConnectionUtils.getJedis();
        try {
            jedis.zrem("STUDENT_SET", JSON.toJSONString(studentOld));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }


}
