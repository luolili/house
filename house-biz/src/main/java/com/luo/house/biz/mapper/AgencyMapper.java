package com.luo.house.biz.mapper;

import com.luo.house.common.model.Agency;
import com.luo.house.common.model.House;
import com.luo.house.common.model.User;
import com.luo.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {

    List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

    Long selectPageCount(@Param("house") House house);

    int insert(Agency agency);


    int delete(String value);


    int update(User updateUser);


    List<Agency> select(Agency agency);


    Long selectAgentCount(@Param("user") User user);


}
