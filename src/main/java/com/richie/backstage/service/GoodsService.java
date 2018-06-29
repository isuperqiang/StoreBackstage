package com.richie.backstage.service;

import com.richie.backstage.dao.CategoryMapper;
import com.richie.backstage.dao.GoodsMapper;
import com.richie.backstage.domain.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@Service
public class GoodsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private GoodsMapper goodsMapper;
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @CacheEvict(value = "deleteGoods", key = "'goods_count'")
    public boolean createGoods(Goods goods, int userId) {
        try {
            int key = goodsMapper.createGoods(goods.getGname(), goods.getSpecification(), goods.getPrice(), goods.getStock(),
                    goods.getSaleVolume(), goods.getCost(), userId);
            return key > 0;
        } catch (SQLException e) {
            logger.error("create goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "deleteGoods", key = "'goods_count'")
    public boolean updateGoods(Goods goods) {
        int catId = categoryMapper.queryCatIdByName(goods.getCategory().getName());
        try {
            int affected = goodsMapper.updateGoods(goods.getGname(), goods.getSpecification(), goods.getPrice(), goods.getStock(),
                    goods.getSaleVolume(), goods.getCost(), goods.isOnSale(), catId, goods.getGoodsId());
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "deleteGoods", key = "'goods_count'")
    public boolean deleteGoods(int goodsId) {
        try {
            int affected = goodsMapper.deleteGoods(goodsId);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("delete goods failed", e);
        }
        return false;
    }

    //@Cacheable(value = "goodsByPage", key = "'goodsPage'")
    public List<Goods> queryGoodsByPage(int pageIndex, int pageSize, String name, int userId) {
        if (--pageIndex < 0) {
            pageIndex = 0;
        }
        if (name == null) {
            name = "";
        }
        return goodsMapper.queryGoodsByPage(name, pageIndex * pageSize, pageSize, userId);
    }

    @Cacheable(value = "queryCount", key = "'goods_count'")
    public int queryCount(int userId) {
        return goodsMapper.queryCount(userId);
    }

    public Goods queryGoodsById(int goodsId) {
        return goodsMapper.queryGoodsById(goodsId);
    }

    @CacheEvict(value = "deleteGoods", key = "'goods_count'")
    public boolean updateGoodsPicture(int goodsId, String picture) {
        try {
            int affected = goodsMapper.updateGoodsImage(goodsId, picture);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update goods failed", e);
        }
        return false;
    }
}
