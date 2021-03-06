package com.luo.house.biz.mapper;

import com.luo.house.common.model.Community;
import com.luo.house.common.model.House;
import com.luo.house.common.model.User;
import com.luo.house.common.model.UserMsg;
import com.luo.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {

    List<House> selectPageHouses(@Param("house") House house, @Param("pageParams") PageParams pageParams);

    Long selectPageCount(@Param("house") House house);

    int insert(User account);


    int delete(String value);


    int update(User updateUser);


    List<User> selectUsersByQuery(User user);


    List<Community> selectCommunity(Community communinty);


    void insertUserMsg(UserMsg userMsg);


}
