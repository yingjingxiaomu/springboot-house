package com.jk.model.house;


import java.io.Serializable;
import java.math.BigInteger;

public class CommunityBean implements Serializable {

  //主键ID
  private String id;
  //省市县主键id
  private BigInteger taId;
  //小区名称
  private String name;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public BigInteger getTaId() {
    return taId;
  }

  public void setTaId(BigInteger taId) {
    this.taId = taId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
