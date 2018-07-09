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
     * @param goods
     * @return
     * @throws SQLException
     */
    int createGoods(@Param("goods") Goods goods) throws SQLException;

    /**
     * 更新商品
     *
     * @param goods
     * @return
     * @throws SQLException
     */
    int updateGoods(@Param("goods") Goods goods) throws SQLException;

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
     * 按状态查询
     *
     * @param sale
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Goods> queryGoodsByPageSale(@Param("sale") int sale, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     * 查询商品数量
     *
     * @param userId
     * @return
     */
    int queryCount(@Param("user_id") int userId);

    /**
     * 根据状态查询数量
     *
     * @param sale
     * @param userId
     * @return
     */
    int queryCountBySale(@Param("sale") int sale, @Param("user_id") int userId);

    /**
     * 更新商品的图片
     *
     * @param goodsId
     * @param picture
     * @return
     * @throws SQLException
     */
    int updateGoodsImage(@Param("goods_id") int goodsId, @Param("picture") String picture) throws SQLException;

    /**
     * 加库存
     *
     * @param goodsId
     * @param stock
     * @return
     * @throws SQLException
     */
    int increaseStock(@Param("goods_id") int goodsId, @Param("stock") int stock) throws SQLException;

    /**
     * 上架和下架
     *
     * @param goodsId
     * @param sale
     * @return
     * @throws SQLException
     */
    int changeSale(@Param("goods_id") int goodsId, @Param("sale") boolean sale) throws SQLException;
}
