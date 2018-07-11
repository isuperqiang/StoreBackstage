package com.richie.backstage.controller;

import com.richie.backstage.config.Constant;
import com.richie.backstage.domain.Goods;
import com.richie.backstage.domain.User;
import com.richie.backstage.response.ListResult;
import com.richie.backstage.response.Result;
import com.richie.backstage.service.GoodsService;
import com.richie.backstage.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author richie on 2018.06.26
 */
@Controller
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    // {"gname":"water", "picture":"3", "price":3,"specification":"bottle","stock":11,"cost":1,"saleVolume":100,"category":{"catId":1}}
    @PostMapping("/goods/create")
    @ResponseBody
    public Result createGoods(@RequestBody Goods goods, @CookieValue(Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        goods.setUser(new User(userId));
        boolean created = goodsService.createGoods(goods);
        if (created) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_GOODS_FAILED);
        }
    }

    // {"goodsId":2,"gname":"water", "picture":"fjiudald", "price":3,"specification":"bottle","stock":11,"cost":1,"saleVolume":100,"category":{"catId":1}}
    @PostMapping("/goods/update")
    @ResponseBody
    public Result updateGoods(@RequestBody Goods goods) {
        boolean updated = goodsService.updateGoods(goods);
        if (updated) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_GOODS_FAILED);
        }
    }

    @PostMapping("/goods/delete")
    @ResponseBody
    public Result deleteGoods(@RequestBody Goods goods) {
        boolean del = goodsService.deleteGoods(goods.getGoodsId());
        if (del) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_GOODS_FAILED);
        }
    }

    @PostMapping("/goods/delete_bundle")
    @ResponseBody
    public Result deleteGoods(@RequestBody List<Goods> goods) {
        boolean ret = true;
        for (Goods good : goods) {
            boolean del = goodsService.deleteGoods(good.getGoodsId());
            ret &= del;
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_GOODS_FAILED);
        }
    }

    @PostMapping("/goods/query")
    @ResponseBody
    public ListResult queryGoodsByPage(@RequestParam("page_index") int pageIndex, @RequestParam("page_size") int pageSize,
                                       @RequestParam(value = "name", required = false) String name, @RequestParam("sale") int sale,
                                       @CookieValue(Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        List<Goods> goods = goodsService.queryGoodsByPage(pageIndex, pageSize, name, sale, userId);
        if (goods != null) {
            int count;
            if (StringUtils.isEmpty(name)) {
                count = goodsService.queryCount(userId, sale);
            } else {
                count = goods.size();
            }
            return ListResult.createOk(goods, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_GOODS_FAILED.getMessage());
        }
    }

    @GetMapping("/goods_show")
    public ModelAndView showGoods(@RequestParam("goods_id") int goodsId) {
        Goods goods = goodsService.queryGoodsById(goodsId);
        ModelAndView modelAndView = new ModelAndView("goods_show");
        modelAndView.addObject("goods", goods);
        return modelAndView;
    }

    @GetMapping("/goods_edit")
    public ModelAndView editGoods(@RequestParam(value = "goods_id") int goodsId) {
        ModelAndView modelAndView = new ModelAndView("goods_edit");
        Goods goods = goodsService.queryGoodsById(goodsId);
        modelAndView.addObject("goods", goods);
        return modelAndView;
    }

    @GetMapping("/goods_create")
    public ModelAndView createGoods() {
        ModelAndView modelAndView = new ModelAndView("goods_edit");
        modelAndView.addObject(new Goods());
        return modelAndView;
    }

    @PostMapping("/goods/increase_stock")
    @ResponseBody
    public Result increaseStock(@RequestBody List<Goods> goods) {
        boolean ret = true;
        for (Goods good : goods) {
            ret &= goodsService.increaseStock(good.getGoodsId(), good.getStock());
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.INCREASE_GOODS_FAILED);
        }
    }

    @PostMapping("/goods/on_sale")
    @ResponseBody
    public Result changeOnSale(@RequestBody List<Goods> goods) {
        boolean ret = true;
        for (Goods good : goods) {
            ret &= goodsService.changeSale(good.getGoodsId(), good.isOnSale());
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CHANGE_SALE_GOODS_FAILED);
        }
    }

}
