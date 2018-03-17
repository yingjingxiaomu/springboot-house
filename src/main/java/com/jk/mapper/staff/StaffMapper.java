package com.jk.mapper.staff;

import com.jk.model.staff.EmpBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {

    // 查询 计算总条数 分页
    @Select("SELECT count(1) from t_emp")
    long queryStaffList();

    @Select("SELECT id,NAME,weixin,photo,phonenumer,loginnumber,password FROM t_emp LIMIT #{page},#{rows}")
    List<EmpBean> queryStaffListPage(@Param("page") Integer page, @Param("rows") Integer limit);

    //修改
    @Update("  UPDATE  t_emp SET \n" +
            "  name=#{name},weixin=#{weixin},photo=#{photo}," +
            "  phonenumer=#{phonenumer},loginnumber=#{loginnumber}," +
            "  password=#{password} WHERE id = #{id}")
    void updStaffList(EmpBean empBean);

    //添加
    @Insert("  INSERT INTO t_emp \n" +
            "  (id,name,weixin,photo,phonenumer,loginnumber,password)\n" +
            "  VALUES\n" +
            "  (#{id},#{name},#{weixin},#{photo},#{phonenumer},#{loginnumber},#{password})")
    void addStaffList(EmpBean empBean);

    //删除单删
    @Delete("DELETE FROM t_emp WHERE id = #{id}")
    void delStaffById(@Param("id") String id);

    //删除批删
    @Delete({
            "<script>"
                    + "DELETE FROM t_emp WHERE id IN  "
                    + "<foreach item='ids' index='index' collection='ids' open='(' separator=',' close=')'>"
                    +       "#{ids}"
                    + "</foreach>"
                    +"</script>"
    })
    void delallStaffByIds( @Param("ids") String[] ids);

    //修改单条查询通过ID
    @Select("SELECT * from t_emp where id=#{id}")
    EmpBean getStaff(@Param("id")String id);
}
