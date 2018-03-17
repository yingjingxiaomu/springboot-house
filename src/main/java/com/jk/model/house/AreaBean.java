package com.jk.model.house;


import java.io.Serializable;
import java.math.BigInteger;

public class AreaBean implements Serializable {

  //主键Id
  private BigInteger id;
  //省
  private String province;
  //市
  private String city;
  //县
  private String county;


  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

}
