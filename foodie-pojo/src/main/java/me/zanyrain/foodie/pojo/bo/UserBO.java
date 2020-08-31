package me.zanyrain.foodie.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户注册所用实体类")
public class UserBO {
    @ApiModelProperty(value = "用户名",required = true,name = "username",example = "9bishi")
    private String username;
    @ApiModelProperty(value = "密码（不得少于六位）",required = true,name = "password",example = "114514")
    private String password;
    @ApiModelProperty(value = "确认密码（不得少于六位，须与密码值一致，登录时为空）",required = false,name = "confirmPassword",example = "114514")
    private String confirmPassword;
}