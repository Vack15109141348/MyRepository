package com.dhcc.dao;

import com.dhcc.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-22 14:37:58
 */
@Mapper
public interface ActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param actcode 主键
     * @return 实例对象
     */
    Activity queryById(Integer actcode);
    /**
     * 查询某条活动参与的情况
     */
   List <Activity> queryByactid(Integer actid);
    /**
     * 查询某个班级发布活动的情况
     */
    List<Activity> queryByUserClass(Integer userclass, Integer flag);
    /**
     * 查询所有已发布活动
     */
    List<Activity> queryAllAct();
    /**
     * 通过userid和flag查询某位用户发布的活动
     *
     * @param userid
     * @return 实例对象
     */
    List<Activity> queryByUserid(Integer userid);
    /**
     * 通过userid和flag查询某位用户参加的活动
     *
     * @param userid
     * @return 实例对象
     */
    List<Activity> queryByUseridflag(Integer userid);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param activity 实例对象
     * @return 对象列表
     */
    List<Activity> queryAll(Activity activity);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param *actcode 主键
     * @return 影响行数
     */
    int deleteById(Activity activity);

}