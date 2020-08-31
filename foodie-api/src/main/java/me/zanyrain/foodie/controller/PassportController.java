package me.zanyrain.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zanyrain.foodie.common.util.CookieUtils;
import me.zanyrain.foodie.common.util.CryptoUtils;
import me.zanyrain.foodie.common.util.JsonUtils;
import me.zanyrain.foodie.pojo.User;
import me.zanyrain.foodie.pojo.bo.UserBO;
import me.zanyrain.foodie.service.UserService;
import me.zanyrain.foodie.common.vo.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("passport")
@Api(value = "吃货通行证", tags = {"用户注册、登录、退出"})
public class PassportController {
    private static final Integer REQUIRED_PASSWORD_LENGTH = 6;
    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    @ApiOperation(httpMethod = "GET", value = "判断用户名是否存在")
    public JSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不能为空！");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已经存在！");
        }
        return JSONResult.ok();
    }

    @PostMapping("/register")
    @ApiOperation(httpMethod = "POST", value = "用户注册")
    public JSONResult register(@RequestBody UserBO userBO,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isAnyBlank(username, password, confirmPassword)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        if (password.length() < REQUIRED_PASSWORD_LENGTH) {
            return JSONResult.errorMsg("密码长度不能少于六位");
        }

        if (!password.equals(confirmPassword)) {
            return JSONResult.errorMsg("两次密码的长度不一致");
        }

        User user = userService.createUser(userBO);
        setNullProperty(user);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(user),true);

        return JSONResult.ok();
    }

    @PostMapping("/login")
    @ApiOperation(httpMethod = "POST", value = "用户登录")
    public JSONResult login(@RequestBody UserBO userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        if (StringUtils.isAnyBlank(username, password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        String cryptoPassword = CryptoUtils.encodeUserPassword(password);
        User user = userService.queryUserForLogin(username,cryptoPassword);

        if (user == null){
            return JSONResult.errorMsg("用户名或密码不正确");
        }

        setNullProperty(user);
        CookieUtils.setCookie(request,response,"user",JsonUtils.objectToJson(user),true);
        return JSONResult.ok(user);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        return JSONResult.ok();
    }

    /**
     * 数据脱敏
     *
     * @param user
     */
    private void setNullProperty(User user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
    }

}