package com.richie.backstage.dao;

import com.richie.backstage.domain.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author richie on 2018.06.25
 */
@Mapper
@Repository
public interface StoreMapper {

    /**
     * 创建店铺
     *
     * @param store
     * @return affected rows
     * @throws SQLException
     */
    int createStore(@Param("store") Store store) throws SQLException;

    /**
     * 更新店铺
     *
     * @param store
     * @return affected rows
     * @throws SQLException
     */
    int updateStore(@Param("store") Store store) throws SQLException;

    /**
     * 查询店铺
     *
     * @param userId
     * @return store
     */
    Store queryStore(@Param("user_id") int userId);

    /**
     * 更新店铺的 logo
     *
     * @param storeId
     * @param logo
     * @return
     * @throws SQLException
     */
    int updateStoreLogo(@Param("store_id") int storeId, @Param("logo") String logo) throws SQLException;
}
