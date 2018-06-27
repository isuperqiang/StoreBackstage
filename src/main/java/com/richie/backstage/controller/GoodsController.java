package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.Goods;
import com.richie.backstage.handler.ListResult;
import com.richie.backstage.handler.Result;
import com.richie.backstage.service.GoodsService;
import com.richie.backstage.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    // {"gname":"water", "picture":"3", "price":3,"specification":"bottle","stock":11,"cost":1,"saleVolume":100,"category":{"catId":1}}
    @PostMapping("/create")
    public Result createGoods(@RequestBody Goods goods, @CookieValue(Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        boolean created = goodsService.createGoods(goods, userId);
        if (created) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_GOODS_FAILED);
        }
    }

    // {"goodsId":2,"gname":"water", "picture":"fjiudald", "price":3,"specification":"bottle","stock":11,"cost":1,"saleVolume":100,"category":{"catId":1}}
    @PostMapping("/update")
    public Result updateGoods(@RequestBody Goods goods) {
        boolean updated = goodsService.updateGoods(goods);
        if (updated) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_GOODS_FAILED);
        }
    }

    @PostMapping("/delete")
    public Result deleteGoods(@RequestParam("goods_id") int goodsId) {
        boolean del = goodsService.deleteGoods(goodsId);
        if (del) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_GOODS_FAILED);
        }
    }

    @PostMapping("/query")
    public ListResult queryGoodsByPage(@RequestParam("page_index") int pageIndex, @RequestParam("page_size") int pageSize,
                                       @RequestParam(value = "name", required = false) String name,
                                       @CookieValue(Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        List<Goods> goods = goodsService.queryGoodsByPage(pageIndex, pageSize, name, userId);
        if (goods != null) {
            int count;
            if (StringUtils.isEmpty(name)) {
                count = goodsService.queryCount(userId);
            } else {
                count = goods.size();
            }
            return ListResult.createOk(goods, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_GOODS_FAILED.getMessage());
        }
    }
}
