package com.bjpowernode.crm.workbench.service.Impl;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public boolean save(Activity a) {
        boolean flag = true;
        int count = activityDao.save(a);
        if (count !=1){
            flag=false;
        }
        return flag;
    }

    public PaginationVO pageList(Map<String, Object> map) {
        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList = activityDao.getDataListByCondition(map);

        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        //返回vo
        return vo;
    }

    public boolean delete(String[] ids) {
        boolean flag = true;
        int count1 = activityRemarkDao.getCountByAids(ids);

        int count2 = activityRemarkDao.deleteByAids(ids);

        if (count1!=count2){
            flag = false;
        }

        int count3 = activityDao.deleteByAids(ids);
        if (count3!=ids.length){
            flag = false;
        }

        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {
        //取uList
        List uList = userDao.getUserList();
        //取a
        Activity a = activityDao.getById(id);
        //将uList和a放入map
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);
        //返回map
        return map;
    }

    public boolean update(Activity a) {
        boolean flag = true;
        int count = activityDao.update(a);
        if (count !=1){
            flag=false;
        }
        return flag;
    }

    public Activity detail(String id) {
        Activity a = activityDao.detail(id);
        return a;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> list = activityRemarkDao.getRemarkListByAid(activityId);

        return list;
    }

    public boolean deleteRemark(String id) {
        boolean flag = true;
        int count = activityRemarkDao.deleteRemark(id);
        if (count !=1){
            flag=false;
        }
        return flag;
    }
}
