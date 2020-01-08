package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.GoodsMapper;
import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Goods> findGoodsByStatus(int id, String status) {
        List<Goods> list = goodsMapper.findGoodsByStatus(id, status);
        return list;
    }

    @Override
    public int addGoods(Goods goods) {
        int result = goodsMapper.addGoods(goods);
        return result;
    }

    @Override
    public int updateGoods(Goods goods) {
        int result = goodsMapper.updateGoods(goods);
        return result;
    }

    @Override
    public int updateGoodsStatus(Goods goods) {
        int result = goodsMapper.updateGoodsStatus(goods);
        return result;
    }

    @Override
    public Goods findGoodsById(int id) {
        return goodsMapper.findGoodsById(id);
    }

    @Override
    public int delete(int id) {
        return goodsMapper.deleteGoods(id);
    }

    @Override
    public int buyGoods(int buyerid, int goodsid, String time, String status) {
        return goodsMapper.buyGoods(buyerid, goodsid, time, status);
    }

    @Override
    public List<Goods> findWantByStatus(int buyerid, String status) {
        return goodsMapper.findGoodsByBuyerId(buyerid, status);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsMapper.findAllGoods();
    }
}
