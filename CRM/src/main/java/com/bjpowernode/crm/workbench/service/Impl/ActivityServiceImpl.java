package com.bjpowernode.crm.workbench.service.Impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);

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
}
