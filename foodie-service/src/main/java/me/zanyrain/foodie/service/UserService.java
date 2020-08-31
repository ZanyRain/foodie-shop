package me.zanyrain.foodie.service;

import me.zanyrain.foodie.pojo.User;
import me.zanyrain.foodie.pojo.bo.UserBO;

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    User createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public User queryUserForLogin(String username, String password);
}
