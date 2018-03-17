package com.jk.mapper.house;


import java.util.Map;

public class SqlProvider {

    /*
    * 分页
    * SQL
    */
    public String bean(Map<String , Object> map){
        StringBuffer house = new StringBuffer();
        try {


        /**
         * Sql省
         */
        final Integer province = (Integer)map.get("province");
        /**
         * Sql市
         */
        final Integer city = (Integer)map.get("city");
        /**
         * Sql县
         */
        final Integer county = (Integer)map.get("county");
        /**
         * Sql标题
         */
        final String title = (String)map.get("title");
        /**
         * Sql最小面积
         */
        final Double startArea= (Double)map.get("startArea");
        /**
         * Sql最大面积
         */
        final Double endArea = (Double)map.get("endArea");
        /**
         * Sql年代
         */
        final String buildingTime = (String)map.get("buildingTime");
        /**
         *Sql室
         */
        final Integer room =(Integer)map.get("room");
        /**
         *Sql厅
         */
        final Integer hall =(Integer)map.get("hall");
        /**
         *Sql卫生间
         */
        final Integer toilet =(Integer)map.get("toilet");
        /**
         * Sql装修情况
         */
        final String decorate = (String)map.get("decorate");




        house.append("SELECT a.id,a.teId,a.title,a.price,a.room,a.hall,a.toilet,a.community,a.province,a.city,a.county,a.buildingTime,a.roomType,a.roomDirection,a.floor,a.decorate,a.unitPrice,a.monthlyPayments,a.sellingPoint,a.ownerMentality,a.communityComplete,a.serviceIntroduce,a.releaseTime,a.roomNum FROM t_sell_house_resource a\n" +
                "LEFT JOIN t_emp te ON a.teId=te.id\n" +
                "LEFT JOIN t_house_type ty ON a.roomType=ty.id\n" +
                "LEFT JOIN t_decorate td ON a.decorate=td.id\n" +
                "LEFT JOIN t_community tm ON a.community=tm.id\n" +
                "LEFT JOIN t_sell_house_pic tp ON a.id=tp.tsId");
        house.append("   WHERE 1=1");
        if( province != null && province != -1 ){
            house.append("  and");
            house.append("  a.province = #{province}");
        }
        if(city != null && city != -1 ){
            house.append("  and");
            house.append("  a.city = #{city}");
        }
        if( county != null && county != -1 ){
            house.append("  and");
            house.append("  a.county = #{county}");
        }

         if(title != null && title != ""){
             house.append("  and");
             house.append("  a.title LIKE '%"+ title+ "%' ");
         }
         if( room != null && room != 0){
             house.append("  and");
             house.append("  a.room = #{room}");
         }
         if( hall != null && hall != 0){
             house.append("  and");
             house.append("  a.hall = #{hall}");
         }
         if( toilet != null && toilet != 0 ){
             house.append("  and");
             house.append("  a.toilet = #{toilet}");
         }
         if( decorate != null && decorate != ""){
             house.append("  and");
             house.append("  a.decorate = #{decorate}");
         }
         if(startArea != null && startArea != 0){
             house.append("  and");
             house.append("  a.area >= #{startArea}");
         }
         if(endArea != null && endArea != 0){
             house.append("  and");
             house.append("  a.area <= #{endArea}");
         }
         if(buildingTime != null && buildingTime != ""){
             house.append("  and");
             house.append("  a.buildingTime = #{buildingTime}");
         }


        house.append("   LIMIT #{page},#{limit}");

        }catch (Exception e){

            e.printStackTrace();
        }
        return house.toString();
    }

    /*
    * 查询所有
    *   SQL
    */
    public String beanCount(Map<String , Object> map){
        StringBuffer house = new StringBuffer();
        try {


            /**
             * Sql省
             */
            final Integer province = (Integer)map.get("province");
            /**
             * Sql市
             */
            final Integer city = (Integer)map.get("city");
            /**
             * Sql县
             */
            final Integer county = (Integer)map.get("county");
            /**
             * Sql标题
             */
            final String title = (String)map.get("title");
            /**
             * Sql最小面积
             */
            final Double startArea= (Double)map.get("startArea");
            /**
             * Sql最大面积
             */
            final Double endArea = (Double)map.get("endArea");
            /**
             * Sql年代
             */
            final String buildingTime = (String)map.get("buildingTime");
            /**
             *Sql室
             */
            final Integer room =(Integer)map.get("room");
            /**
             *Sql厅
             */
            final Integer hall =(Integer)map.get("hall");
            /**
             *Sql卫生间
             */
            final Integer toilet =(Integer)map.get("toilet");
            /**
             * Sql装修情况
             */
            final String decorate = (String)map.get("decorate");





            house.append("SELECT a.id,a.teId,a.title,a.price,a.room,a.hall,a.toilet,a.community,a.province,a.city,a.county,a.buildingTime,a.roomType,a.roomDirection,a.floor,a.decorate,a.unitPrice,a.monthlyPayments,a.sellingPoint,a.ownerMentality,a.communityComplete,a.serviceIntroduce,a.releaseTime,a.roomNum FROM t_sell_house_resource a\n" +
                    "LEFT JOIN t_emp te ON a.teId=te.id\n" +
                    "LEFT JOIN t_house_type ty ON a.roomType=ty.id\n" +
                    "LEFT JOIN t_decorate td ON a.decorate=td.id\n" +
                    "LEFT JOIN t_community tm ON a.community=tm.id\n" +
                    "LEFT JOIN t_sell_house_pic tp ON a.id=tp.tsId");
            house.append("  WHERE 1=1");
            if( province != null && province != -1 ){
                house.append("  and");
                house.append("  a.province = #{province}");
            }
            if(city != null && city != -1 ){
                house.append("  and");
                house.append("  a.city = #{city}");
            }
            if( county != null && county != -1 ){
                house.append("  and");
                house.append("  a.county = #{county}");
            }

            if(title != null && title != ""){
                house.append("  and");
                house.append("  a.title LIKE '%"+ title+ "%' ");
            }
            if( room != null && room != 0){
                house.append("  and");
                house.append("  a.room = #{room}");
            }
            if( hall != null && hall != 0){
                house.append("  and");
                house.append("  a.hall = #{hall}");
            }
            if( toilet != null && toilet != 0 ){
                house.append("  and");
                house.append("  a.toilet = #{toilet}");
            }
            if( decorate != null && decorate != ""){
                house.append("  and");
                house.append("  a.decorate = #{decorate}");
            }
            if(startArea != null && startArea != 0){
                house.append("  and");
                house.append("  a.area >= #{startArea}");
            }
            if(endArea != null && endArea != 0){
                house.append("  and");
                house.append("  a.area <= #{endArea}");
            }
            if(buildingTime != null && buildingTime != ""){
                house.append("  and");
                house.append("  a.buildingTime = #{buildingTime}");
            }

            System.out.println(house.toString());

        }catch (Exception e){

            e.printStackTrace();
        }
        return house.toString();
    }



}
