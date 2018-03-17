package com.jk.mapper.house;

import com.jk.model.house.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface HouseMapper {

    /**
     * 新增房屋
     * @param srb
     */
    @Insert(" INSERT INTO t_sell_house_resource \n" +
            "  (id,title,price,room,hall,toilet,area,community,province,city,county,buildingTime,roomType,roomDirection,\n" +
            "  floor,decorate,unitPrice,monthlyPayments,sellingPoint,ownerMentality,communityComplete,serviceIntroduce,releaseTime,roomNum)\n" +
            "  VALUES\n" +
            "  (#{id},#{title},#{price},#{room},#{hall},#{toilet},#{area},#{community},#{province},#{city},#{county},#{buildingTime},#{roomType},#{roomDirection},\n" +
            "  #{floor},#{decorate},#{unitPrice},#{monthlyPayments},#{sellingPoint},#{ownerMentality},#{communityComplete},#{serviceIntroduce},#{releaseTime},#{roomNum}) ")
    void addhouse(SellHouseResourceBean srb);

    @Select("SELECT id,NAME FROM xx_area WHERE parent = #{pid}")
    List<AreaBean> queryArea(@Param("pid") Integer pid);

    @Select("SELECT id,NAME FROM xx_area WHERE parent = 0")
    List<XxArea> selectProvince();

    @Select("SELECT id,NAME FROM xx_area WHERE parent = #{pid}")
    List<XxArea> selectArea(Integer pid);



    /**
     * 修改房屋
     * @param srb
     */
    @Update(" UPDATE t_sell_house_resource SET\n" +
           " title=#{title},price=#{price},room=#{room},hall=#{hall},toilet=#{toilet},area=#{area},\n" +
           " community=#{community},province=#{province},city=#{city},county=#{county},buildingTime=#{buildingTime},\n" +
           " roomType=#{roomType},roomDirection=#{roomDirection},floor=#{floor},decorate=#{decorate},unitPrice=#{unitPrice},\n" +
           " monthlyPayments=#{monthlyPayments},sellingPoint=#{sellingPoint},ownerMentality=#{ownerMentality},\n" +
           " communityComplete=#{communityComplete},serviceIntroduce=#{serviceIntroduce},releaseTime=#{releaseTime},roomNum=#{roomNum}\n" +
           " WHERE id = #{id}")
    void updatehouse(SellHouseResourceBean srb);

    @Select("SELECT id,NAME FROM xx_area WHERE ID = #{province}")
    XxArea getgetAreaById(@Param("province") Integer province);

    /**
     * 批量添加图片
     * @param sell
     * @return
     */
    @Insert("<script>"+
            "insert into t_sell_house_pic(id,tsId,url,type) "
            + "values "
            + "<foreach collection='list' item='list' separator =','> "
            + "(#{list.id},#{list.tsId},#{list.url},#{list.type}) "
            + "</foreach> "
            + "</script>")
    boolean addHousePic(@Param("list") ArrayList<SellHousePicBean> sell);


    @Select("SELECT id,NAME FROM xx_area WHERE id = #{county}")
    List<XxArea> selectAreatwo(@Param("county") Integer county);

    /**
     * @看程度
     * @return
     */
    @Select("select * from t_decorate")
    List<DecorateBean> selectDecorate();

    /**
     * 看房子的类型
     * @return
     */
    @Select("SELECT * FROM t_house_type")
    List<HouseTypeBean> selectHouseType();


    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from t_sell_house_resource where id=#{id}")
    SellHouseResourceBean getHouseDetailsId(@Param("id") String id);



    @Select("SELECT count(1) from t_sell_house_resource")
    long queryHouseList();


    @Select("SELECT * FROM t_sell_house_resource LIMIT #{page},#{rows}")
    List<SellHouseResourceBean> queryHouseListPage(@Param("page") Integer page, @Param("rows") Integer limit);

    //删除单删
    @Delete("DELETE FROM t_sell_house_resource WHERE id = #{id}")
    void delHouseById(@Param("id") String id);

    //删除批删
    @Delete({
            "<script>"
                    + "DELETE FROM t_sell_house_resource WHERE id IN  "
                    + "<foreach item='ids' index='index' collection='ids' open='(' separator=',' close=')'>"
                    +       "#{ids}"
                    + "</foreach>"
                    +"</script>"
    })
    void delAllHouseByIds(@Param("ids") String[] idss);
}
