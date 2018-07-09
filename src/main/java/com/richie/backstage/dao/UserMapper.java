package com.richie.backstage.dao;

import com.richie.backstage.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author richie on 2018.06.25
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    User login(@Param("phone") String phone, @Param("password") String password);

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @return affected rows
     * @throws SQLException
     */
    int register(@Param("phone") String phone, @Param("password") String password) throws SQLException;

    /**
     * 检查手机号是否被占用
     *
     * @param phone
     * @return
     */
    int findCountByPhone(@Param("phone") String phone);

    /**
     * 根据 ID 查询用户
     *
     * @param userId
     * @return
     */
    User findUserById(@Param("user_id") int userId);

    /**
     * 更新登录时间
     *
     * @param userId last
     * @return affected rows
     * @throws SQLException
     */
    int updateVisitTime(@Param("user_id") int userId) throws SQLException;

    /**
     * 查询最后插入的用户 ID
     *
     * @return
     */
    int findLastUserId();
}
