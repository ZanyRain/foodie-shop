package me.zanyrain.foodie.service.impl;

import me.zanyrain.foodie.common.enums.Sex;
import me.zanyrain.foodie.common.util.CryptoUtils;
import me.zanyrain.foodie.common.util.DateUtils;
import me.zanyrain.foodie.mapper.UserMapper;
import me.zanyrain.foodie.pojo.User;
import me.zanyrain.foodie.pojo.bo.UserBO;
import me.zanyrain.foodie.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", username);

        User user = userMapper.selectOneByExample(userExample);

        return (user != null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(UserBO userBO) {
        String uid = Sid.nextShort();
        String password = CryptoUtils.encodeUserPassword(userBO.getPassword());
        User user =  new User().builder()
                .id(uid)
                .username(userBO.getUsername())
                .nickname(userBO.getUsername())
                .sex(Sex.SECRET.type)
                .birthday(DateUtils.stringToDate("1970-01-01"))
                .face(DEFAULT_FACE)
                .password(password)
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserForLogin(String username, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);

        User user = userMapper.selectOneByExample(example);

        return user;
    }
}
