package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.GoodsMapper;
import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> findOnSaleGoods() {
        List<Goods> list = goodsMapper.findOnSaleGoods();
        return list;
    }

    @Override
    public List<Goods> findMyOnSaleGoods(int id) {
        List<Goods> list = goodsMapper.findMyOnSaleGoods(id);
        return list;
    }

    @Override
    public int addGoods(Goods goods) {
        int result = goodsMapper.addGoods(goods.getTitle(), goods.getDetail(), goods.getPrice(), goods.getPurchaseUrl(), goods.getLocation(), goods.getNumber(), goods.getSellerid());
        return result;
    }


}
