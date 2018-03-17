package com.jk.mapper.login;


import com.jk.model.staff.EmpBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {

    //登录查询  是否数据库中用户存在
    @Select("SELECT id,NAME,weixin,photo,phonenumer,loginnumber,PASSWORD FROM t_emp WHERE loginnumber = #{loginnumber}")
    EmpBean getlogin(@Param("loginnumber") String loginnumber);
}
