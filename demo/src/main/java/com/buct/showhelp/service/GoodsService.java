package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Goods;

import java.util.List;

public interface GoodsService {
    //所有在售
    List<Goods> findOnSaleGoods();

    //我的货物
    List<Goods> findMyOnSaleGoods(int id);

    //添加闲置
    int addGoods(Goods goods);

    //修改描述
    int updateGoods(Goods goods);

    //更新状态
    int updateGoodsStatus(Goods goods);

    //以id获取一个物品
    Goods findGoodsById(int id);
}
