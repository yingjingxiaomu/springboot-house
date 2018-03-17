package com.jk.service.house;

import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.house.HouseMapper;

import com.jk.model.house.*;
import com.jk.model.staff.EmpBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    /**
     * 新增房屋
     * @param srb
     */
    @Override
    public void addhouse(SellHouseResourceBean srb) {
      houseMapper.addhouse(srb);
    }

    /**
     * 修改
     * @param srb
     */
    @Override
    public void updatehouse(SellHouseResourceBean srb) {
        houseMapper.updatehouse(srb);
    }

    /**
     * 添加图片 批量
     * @param sell
     * @return
     */
    @Override
    public boolean addHousePic(ArrayList<SellHousePicBean> sell) {

        return houseMapper.addHousePic(sell);
    }

    /**
     * 三级主
     * @return
     */
    @Override
    public List<XxArea> selectProvince() {
        return houseMapper.selectProvince();
    }

    @Override
    public List<XxArea> selectArea(Integer pid) {
        return houseMapper.selectArea(pid);
    }


    @Override
    public XxArea getgetAreaById(Integer province) {
        return houseMapper.getgetAreaById(province);
    }



    //回显市 县
    @Override
    public List<XxArea> selectAreatwo(Integer county) {
        return houseMapper.selectAreatwo(county);
    }




    /**
     * 查看装修房子的程度
     * @return
     */
    @Override
    public List<DecorateBean> selectDecorate() {
        return houseMapper.selectDecorate();
    }

    /**
     * 查看住宅类型
     * @return
     */
    @Override
    public List<HouseTypeBean> selectHouseType() {
        return houseMapper.selectHouseType();
    }




    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public SellHouseResourceBean getHouseDetailsId(String id) {
        return houseMapper.getHouseDetailsId(id);
    }

    /**
     * 查询 房源 分页
     * @param page
     * @param limit
     * @return
     */
    @Override
    public String queryHouseList(Integer page, Integer limit) {
        page =(page-1)*limit;
        long total = houseMapper.queryHouseList();
        List<SellHouseResourceBean> list = houseMapper.queryHouseListPage(page,limit);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", total);
        jsonObject.put("data", list);

        return jsonObject.toString();
    }

    /**
     * 单删
     * @param id
     */
    @Override
    public void delHouseById(String id) {

        houseMapper.delHouseById(id);

    }

    /**
     * 批删
     * @param ids
     */
    @Override
    public void delAllHouseByIds(String ids) {
        String [] idss = ids.split(",");

        houseMapper.delAllHouseByIds(idss);
    }
}
