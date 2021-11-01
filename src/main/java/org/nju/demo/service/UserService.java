package org.nju.demo.service;

import org.nju.demo.entity.AUser;
import org.nju.demo.entity.AUserInfo;

public interface UserService {

    boolean login(AUser user);

    boolean isExist(String username);

    int addUser(AUser user);

    AUser getUserByUsername(String username);

    int updatePassword(AUser user);

    int addUserInfo(AUserInfo userInfo);

    AUserInfo getUserInfoByUserId(int id);

    int updateUserInfo(AUserInfo userInfo);
}
