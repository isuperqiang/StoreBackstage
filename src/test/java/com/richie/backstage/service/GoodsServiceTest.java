package com.richie.backstage.service;

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

import static org.junit.Assert.assertThat;

/**
 * @author richie on 2018.07.09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceTest {

    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Test
    @Transactional
    public void createGoods() {
        Goods goods = new Goods();
        goods.setUser(new User(1));
        Category category = new Category(5);
        category.setName("电脑");
        goods.setCategory(category);
        goods.setCost(1);
        goods.setGname("123");
        goods.setSaleVolume(1);
        goods.setPrice(2);
        goods.setCost(3);
        goods.setStock(1);
        goods.setSpecification("good");
        boolean b = goodsService.createGoods(goods);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void updateGoods() {
        Goods goods = new Goods();
        Category category = new Category(5);
        category.setName("电脑");
        goods.setCategory(category);
        goods.setGoodsId(5);
        goods.setCost(1);
        goods.setGname("123");
        goods.setSaleVolume(1);
        goods.setPrice(2);
        goods.setCost(3);
        goods.setStock(1);
        goods.setSpecification("good");
        boolean b = goodsService.updateGoods(goods);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    @Transactional
    public void deleteGoods() {
        boolean b = goodsService.deleteGoods(5);
        assertThat(b, Matchers.equalTo(true));
    }

    @Test
    public void queryGoodsByPage() {
    }

    @Test
    public void queryCount() {
    }

    @Test
    public void queryGoodsById() {
    }

    @Test
    public void updateGoodsPicture() {
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void changeSale() {
    }
}