package com.jk.model.staff;


import java.io.Serializable;

public class EmpBean implements Serializable {

  //主键Id
  private String id;
  //姓名
  private String name;
  //微信二维码
  private String weixin;
  //照片
  private String photo;
  //联系方式
  private String phonenumer;
  //登录账号
  private String loginnumber;
  //密码(md5 32位)
  private String password;


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


  public String getWeixin() {
    return weixin;
  }

  public void setWeixin(String weixin) {
    this.weixin = weixin;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public String getPhonenumer() {
    return phonenumer;
  }

  public void setPhonenumer(String phonenumer) {
    this.phonenumer = phonenumer;
  }


  public String getLoginnumber() {
    return loginnumber;
  }

  public void setLoginnumber(String loginnumber) {
    this.loginnumber = loginnumber;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
