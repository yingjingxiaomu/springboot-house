package com.jk.service.house;

import com.jk.model.house.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HouseService {
    void addhouse(SellHouseResourceBean srb);



    List<XxArea> selectProvince();

    List<XxArea> selectArea(Integer pid);


    void updatehouse(SellHouseResourceBean srb);

    XxArea getgetAreaById(Integer province);

    boolean addHousePic(ArrayList<SellHousePicBean> sell);

    List<XxArea> selectAreatwo(Integer county);

    List<DecorateBean> selectDecorate();

    List<HouseTypeBean> selectHouseType();



    SellHouseResourceBean getHouseDetailsId(String id);


    String queryHouseList(Integer page, Integer limit);

    void delHouseById(String id);

    void delAllHouseByIds(String ids);
}
