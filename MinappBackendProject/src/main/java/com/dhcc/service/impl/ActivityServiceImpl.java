package com.dhcc.service.impl;

import com.dhcc.entity.Activity;
import com.dhcc.dao.ActivityDao;
import com.dhcc.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-22 14:37:59
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param actcode 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(Integer actcode) {
        return this.activityDao.queryById(actcode);
    }


    /**
     * 查询某个班级发布活动的情况
     */
    @Override
    public List<Activity> queryByUserClass(Integer userclass, Integer flag) {
        return this.activityDao.queryByUserClass(userclass, flag);
    }
    /**
     * 查询某条活动参与的情况
     */
   public  List <Activity> queryByactid(Integer actid){
        return this.activityDao.queryByactid(actid);
    }
    /**
     * 查询所有已发布活动
     */
    @Override
  public  List<Activity> queryAllAct(){
            return this.activityDao.queryAllAct();
    }
    /**
     * 通过userid和flag查询某位用户发布的活动
     *
     * @param userid
     * @return 实例对象
     */
    @Override
  public  List<Activity> queryByUserid(Integer userid){
        return this.activityDao.queryByUserid(userid);
    }
    /**
     * 通过userid和flag查询某位用户发布的活动
     *
     * @param userid
     * @return 实例对象
     */
    @Override
    public  List<Activity> queryByUseridflag(Integer userid){
        return this.activityDao.queryByUseridflag(userid);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Activity> queryAllByLimit(int offset, int limit) {
        return this.activityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Activity activity) {

        return  this.activityDao.insert(activity);
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Activity activity) {

        return this.activityDao.update(activity);
    }

    /**
     * 通过主键删除数据
     *
     * *@param actcode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Activity activity) {

        return this.activityDao.deleteById(activity) > 0;
    }
}