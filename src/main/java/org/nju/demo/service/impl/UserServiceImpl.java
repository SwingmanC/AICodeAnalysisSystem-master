package org.nju.demo.service.impl;

import org.nju.demo.dao.AUserInfoMapper;
import org.nju.demo.dao.AUserMapper;
import org.nju.demo.entity.AUser;
import org.nju.demo.entity.AUserExample;
import org.nju.demo.entity.AUserInfo;
import org.nju.demo.entity.AUserInfoExample;
import org.nju.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AUserMapper userMapper;

    @Autowired
    private AUserInfoMapper userInfoMapper;

    @Override
    public boolean login(AUser user) {

        AUserExample userExample = new AUserExample();
        AUserExample.Criteria criteria = userExample.createCriteria();

        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());

        List<AUser> result = userMapper.selectByExample(userExample);
        if (result.size() == 0)
            return false;
        return true;
    }

    @Override
    public boolean isExist(String username) {
        AUserExample userExample = new AUserExample();
        AUserExample.Criteria criteria = userExample.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<AUser> userList = userMapper.selectByExample(userExample);
        if (userList.size() == 0)
            return false;
        return true;
    }

    @Override
    public int addUser(AUser user) {
        return userMapper.insert(user);
    }

    @Override
    public AUser getUserByUsername(String username) {
        AUserExample userExample = new AUserExample();
        AUserExample.Criteria criteria = userExample.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<AUser> userList = userMapper.selectByExample(userExample);
        return userList.get(0);
    }

    @Override
    public int updatePassword(AUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addUserInfo(AUserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public AUserInfo getUserInfoByUserId(int id) {

        AUserInfoExample example = new AUserInfoExample();
        AUserInfoExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(id);

        List<AUserInfo> userInfoList = userInfoMapper.selectByExample(example);
        if (userInfoList.size() == 0) return null;
        return userInfoList.get(0);
    }

    @Override
    public int updateUserInfo(AUserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
}
