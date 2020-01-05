package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Goods;

import java.util.List;

public interface GoodsService {
    //所有在售
    List<Goods> findOnSaleGoods();

    //我的货物
    List<Goods> findGoodsByStatus(int id, String status);

    //添加闲置
    int addGoods(Goods goods);

    //修改描述
    int updateGoods(Goods goods);

    //更新状态
    int updateGoodsStatus(Goods goods);

    //以id获取一个物品
    Goods findGoodsById(int id);

    //delete goods
    int delete(int id);

    /**
     * asBuyer
     */
    //交易物品
    int buyGoods(int buyerid, int goodsid, String time, String status);

    //as buyer get goods like 买到的物品
    List<Goods> findWantByStatus(int buyerid, String status);
}
