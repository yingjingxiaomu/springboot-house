package com.jk.mapper.tree;

import com.jk.model.tree.TreeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TreeMapper {

    @Select(" SELECT id , pid , title , icon , href , spread FROM t_tree")
    List<TreeBean> getlayLeftTree();
}
