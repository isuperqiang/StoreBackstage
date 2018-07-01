package com.richie.backstage.dao;

import com.richie.backstage.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@Mapper
@Repository
public interface GoodsMapper {

    /**
     * 创建商品
     *
     * @param name
     * @param specification
     * @param price
     * @param stock
     * @param saleVolume
     * @param cost
     * @param userId
     * @param catId
     * @return
     * @throws SQLException
     */
    int createGoods(@Param("name") String name, @Param("specification") String specification,
                    @Param("price") double price, @Param("stock") int stock, @Param("sale_volume") int saleVolume,
                    @Param("cost") double cost, @Param("user_id") int userId, @Param("cat_id") int catId) throws SQLException;

    /**
     * 更新商品
     *
     * @param name
     * @param specification
     * @param price
     * @param stock
     * @param saleVolume
     * @param cost
     * @param onSale
     * @param catId
     * @param goodsId
     * @return
     * @throws SQLException
     */
    int updateGoods(@Param("name") String name, @Param("specification") String specification,
                    @Param("price") double price, @Param("stock") int stock, @Param("sale_volume") int saleVolume,
                    @Param("cost") double cost, @Param("on_sale") boolean onSale, @Param("cat_id") int catId,
                    @Param("goods_id") int goodsId) throws SQLException;

    /**
     * 删除商品
     *
     * @param goodsId
     * @return
     * @throws SQLException
     */
    int deleteGoods(@Param("goods_id") int goodsId) throws SQLException;

    /**
     * 根据 ID 查询商品
     *
     * @param goodsId
     * @return
     */
    Goods queryGoodsById(@Param("goods_id") int goodsId);

    /**
     * 分页查询
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Goods> queryGoodsByPage(@Param("name") String name, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     * 查询商品数量
     *
     * @param userId
     * @return
     */
    int queryCount(@Param("user_id") int userId);

    /**
     * 更新商品的图片
     *
     * @param goodsId
     * @param picture
     * @return
     * @throws SQLException
     */
    int updateGoodsImage(@Param("goods_id") int goodsId, @Param("picture") String picture) throws SQLException;
}
