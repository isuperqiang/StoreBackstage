package com.richie.backstage.dao;

import com.richie.backstage.domain.Category;
import com.richie.backstage.domain.Goods;
import com.richie.backstage.domain.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsMapperTest {
    private GoodsMapper goodsMapper;

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Test
    @Transactional
    public void createGoods() throws SQLException {
        Goods goods = new Goods();
        goods.setUser(new User(1));
        goods.setCategory(new Category(5));
        goods.setCost(1);
        goods.setGname("123");
        goods.setSaleVolume(1);
        goods.setPrice(2);
        goods.setCost(3);
        goods.setStock(1);
        goods.setSpecification("good");
        int i = goodsMapper.createGoods(goods);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void updateGoods() throws SQLException {
        Goods goods = new Goods();
        goods.setCategory(new Category(5));
        goods.setCost(1);
        goods.setGname("123");
        goods.setSaleVolume(1);
        goods.setPrice(2);
        goods.setCost(3);
        goods.setGoodsId(5);
        goods.setStock(1);
        goods.setSpecification("good");
        int i = goodsMapper.updateGoods(goods);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void deleteGoods() throws SQLException {
        int i = goodsMapper.deleteGoods(5);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    public void queryGoodsById() {
        Goods goods = goodsMapper.queryGoodsById(5);
        assertThat(goods, Matchers.notNullValue());
    }

    @Test
    public void queryGoodsByPage() {
        List<Goods> goods = goodsMapper.queryGoodsByPage("", 1, 10, 1);
        assertThat(goods, Matchers.notNullValue());
    }

    @Test
    public void queryGoodsByPageSale() {
        List<Goods> goods = goodsMapper.queryGoodsByPageSale(10, 1, 10, 1);
        assertThat(goods, Matchers.notNullValue());
    }

    @Test
    public void queryCount() {
        int i = goodsMapper.queryCount(1);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    public void queryCountBySale() {
    }

    @Test
    @Transactional
    public void updateGoodsImage() throws SQLException {
        int i = goodsMapper.updateGoodsImage(5, "123");
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void increaseStock() throws SQLException {
        int i = goodsMapper.increaseStock(5, 1);
        assertThat(i, Matchers.greaterThan(0));
    }

    @Test
    @Transactional
    public void changeSale() throws SQLException {
        int i = goodsMapper.changeSale(5, true);
        assertThat(i, Matchers.greaterThan(0));
    }
}