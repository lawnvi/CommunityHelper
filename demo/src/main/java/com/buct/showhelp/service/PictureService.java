package com.buct.showhelp.service;

import com.buct.showhelp.pojo.GoodsPicPath;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PictureService {
    int saveGoodsPicPath(GoodsPicPath path);

    GoodsPicPath getGoodsPicPathById(int id);

    List<GoodsPicPath> getGoodsPicPathByGoods(int id);

    int updateGoodsPicPath(GoodsPicPath path);

    int deleteGoodsPicPathById(int id);

    int deleteGoodsPicPathByGoods(int id);
}
