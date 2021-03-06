package com.luo.house.common.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class Community {
    private Long id;
    private String name;
    private String cityCode;
    private String cityName;
    private String email;
    private String passwd;
    private String confirmPasswd;
    private Integer type;
    private Date createTime;
    private Integer enable;
    private String avator;
    private MultipartFile avatorFile;
    private String newPassword;
    private String key;
    private Integer agencyId;

}
