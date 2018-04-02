package com.demo.angularpractice.account;

import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.repository.UserMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component("userDetailsService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过数据库来查找到实际的用户信息
        SysUserParam userInfo = userMapper.selectByUserName(username);
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在!");
        } else if (!userInfo.isEnabled()) {
            throw new UsernameNotFoundException("用户已被禁用!");
        } else {
            Set<String> strings = userMapper.selectResourcesByUser(userInfo);
            List<Resource> resources = userMapper.selectResourcesByUserId(userInfo.getId());
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            for (Resource resource : resources) {
                if (!Objects.isNull(resource) && !Objects.isNull(resource.getName())) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
//            return new User(userInfo.getUsername(), userInfo.getPassword(), grantedAuthorities);
            return userInfo;
        }

    }

    @Override
    public SysUserParam changePassword(Integer userId, String oldPassword, String newPassword) {
        SysUserParam user = new SysUserParam();
        user.setId(userId);
        SysUserParam sysUserParam = userMapper.selectByUser(user);
        if (!Objects.isNull(sysUserParam)) {
            if (sysUserParam.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userMapper.updateByPrimaryKey(user);
            } else {
                throw new AppException("原密码错误1");
            }
        }
        return sysUserParam;
    }

    @Override
    public SysUserParam registe(SysUserParam user) {
        SysUserParam sysUserParam = userMapper.selectByUser(user);
        if (Objects.isNull(sysUserParam)) {
            userMapper.insertUser(user);
        } else {
            throw new AppException("用户已存在!");
        }
        return user;
    }

    @Override
    public SysUserParam invalidUser(SysUserParam user) {
        SysUserParam sysUserParam = userMapper.selectByUser(user);
        if (!Objects.isNull(sysUserParam)) {
            sysUserParam.setEnabled(Boolean.FALSE);
            userMapper.updateByPrimaryKey(user);
        } else {
            throw new AppException("用户不存在");
        }
        return sysUserParam;
    }

    @Override
    public SysUserParam login(String userName, String password) {
        return null;
    }
}
