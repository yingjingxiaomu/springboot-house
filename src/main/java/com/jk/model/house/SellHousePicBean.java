package com.jk.model.house;


import java.io.Serializable;

public class SellHousePicBean implements Serializable {

  //主键Id
  private String id;
  //卖房的主键id
  private String tsId;
  //图片地址
  private String url;
  //图片类型
  private Integer type;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTsId() {
    return tsId;
  }

  public void setTsId(String tsId) {
    this.tsId = tsId;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
