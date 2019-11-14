package com.luo.house.biz.mapper;

import com.luo.house.common.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    List<User> selectUsers();

    int insert(User account);


    int delete(String value);


    int update(User updateUser);


}
