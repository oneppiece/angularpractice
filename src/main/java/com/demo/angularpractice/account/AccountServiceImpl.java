package com.demo.angularpractice.account;

import com.demo.angularpractice.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("userDetailsService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过数据库来查找到实际的用户信息
        SysUserParam userInfo = userMapper.selectByUserName(username);
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在!");
        } else if (!userInfo.isEnabled()) {
            throw new UsernameNotFoundException("用户已被禁用!");
        } else {
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
