package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.PictureMapper;
import com.buct.showhelp.pojo.GoodsPicPath;
import com.buct.showhelp.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int saveGoodsPicPath(GoodsPicPath path) {
        return pictureMapper.saveGoodsPicPath(path);
    }

    @Override
    public GoodsPicPath getGoodsPicPathById(int id) {
        return pictureMapper.getGoodsPicPathById(id);
    }

    @Override
    public List<GoodsPicPath> getGoodsPicPathByGoods(int id) {
        return pictureMapper.getGoodsPicPathByGoods(id);
    }

    @Override
    public int updateGoodsPicPath(GoodsPicPath path) {
        return pictureMapper.updateGoodsPicPath(path);
    }

    @Override
    public int deleteGoodsPicPathById(int id) {
        return pictureMapper.deleteGoodsPicPathById(id);
    }

    @Override
    public int deleteGoodsPicPathByGoods(int id) {
        return pictureMapper.deleteGoodsPicPathByGoods(id);
    }
}
