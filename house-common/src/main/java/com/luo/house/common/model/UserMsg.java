package com.luo.house.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserMsg {

    private Long id;
    private Long userId;
    private Long houseId;
    private Long agentId;
    private String msg;
    private String email;
    private String username;
    private Date createTime;
}
