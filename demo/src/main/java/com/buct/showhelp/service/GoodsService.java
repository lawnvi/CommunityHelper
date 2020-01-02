package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findOnSaleGoods();

    List<Goods> findMyOnSaleGoods(int id);

    int addGoods(Goods goods);
}
