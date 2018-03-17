package com.jk.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TestMapper {

    @Select("select count(1) from test")
    int getCount();
}
