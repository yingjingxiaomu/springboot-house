package com.jk.model.tree;

import java.io.Serializable;

public class TreeBean implements Serializable {


    private  Integer id;

    private  String  title;

    private  String  icon;

    private  String  href;

    private  String  spread;

    private Integer pid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TreeBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", spread='" + spread + '\'' +
                ", pid=" + pid +
                '}';
    }
}
