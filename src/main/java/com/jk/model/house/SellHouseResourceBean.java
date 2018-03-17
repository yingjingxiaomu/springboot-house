package com.jk.model.house;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellHouseResourceBean implements Serializable {


    //卖房房源主键ID
    private String id;

    //员工表guanlianID
    private String teId;

    //标题
    private String title;

    //售价
    private Integer price;

    //室
    private Integer room;

    //厅
    private Integer hall;

    //卫生间
    private Integer toilet;

    //面积
    private Double area;

    //最小面积
    private Double startArea;

    //最大面积
    private Double endArea;

    //所属小区
    private String community;

    //所在位置省
    private Integer province;

    //所在位置市
    private Integer city;

    //所在位置县
    private Integer county;

    //建造年代
    private String buildingTime;

    //房屋类型
    private String roomType;

    //房屋朝向
    private String roomDirection;

    //所在楼层
    private String floor;

    //装修程度
    private String decorate;

    //参考单价
    private Double unitPrice;

    //参考月供
    private Double monthlyPayments;

    //核心卖点
    private String sellingPoint;

    //业主心态
    private String ownerMentality;

    //小区配套
    private String communityComplete;

    //服务介绍 富文本
    private String serviceIntroduce;

    //发布日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;

    //房屋编号
    private String roomNum;


    @Override
    public String toString() {
        return "SellHouseResourceBean{" +
                "id='" + id + '\'' +
                ", teId='" + teId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", room=" + room +
                ", hall=" + hall +
                ", toilet=" + toilet +
                ", area=" + area +
                ", startArea=" + startArea +
                ", endArea=" + endArea +
                ", community='" + community + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", county=" + county +
                ", buildingTime=" + buildingTime +
                ", roomType='" + roomType + '\'' +
                ", roomDirection='" + roomDirection + '\'' +
                ", floor='" + floor + '\'' +
                ", decorate='" + decorate + '\'' +
                ", unitPrice=" + unitPrice +
                ", monthlyPayments=" + monthlyPayments +
                ", sellingPoint='" + sellingPoint + '\'' +
                ", ownerMentality='" + ownerMentality + '\'' +
                ", communityComplete='" + communityComplete + '\'' +
                ", serviceIntroduce='" + serviceIntroduce + '\'' +
                ", releaseTime=" + releaseTime +
                ", roomNum='" + roomNum + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeId() {
        return teId;
    }

    public void setTeId(String teId) {
        this.teId = teId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    public Integer getToilet() {
        return toilet;
    }

    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public Integer getProvince() {

        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public String getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(String buildingTime) {
        this.buildingTime = buildingTime;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomDirection() {
        return roomDirection;
    }

    public void setRoomDirection(String roomDirection) {
        this.roomDirection = roomDirection;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(Double monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public String getSellingPoint() {
        return sellingPoint;
    }

    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    public String getOwnerMentality() {
        return ownerMentality;
    }

    public void setOwnerMentality(String ownerMentality) {
        this.ownerMentality = ownerMentality;
    }

    public String getCommunityComplete() {
        return communityComplete;
    }

    public void setCommunityComplete(String communityComplete) {
        this.communityComplete = communityComplete;
    }

    public String getServiceIntroduce() {
        return serviceIntroduce;
    }

    public void setServiceIntroduce(String serviceIntroduce) {
        this.serviceIntroduce = serviceIntroduce;
    }

    public String getReleaseTime() {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(releaseTime==null){
            return null;
        }

        return sim.format(releaseTime);
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Double getStartArea() {
        return startArea;
    }

    public void setStartArea(Double startArea) {
        this.startArea = startArea;
    }

    public Double getEndArea() {
        return endArea;
    }

    public void setEndArea(Double endArea) {
        this.endArea = endArea;
    }
}
