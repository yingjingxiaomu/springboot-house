package com.jk.model.house;


import java.io.Serializable;

public class DecorateBean implements Serializable {

  //主键id
  private String id;
  //姓名
  private String name;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
