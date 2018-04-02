package com.demo.angularpractice.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户
 * Created by Administrator on 22:01.
 */
@Setter
public class SysUserParam extends BaseParam implements UserDetails {
    private static final long serialVersionUID = 5274162006516373373L;
    @Getter

    private Integer id;
    @Getter
    private String username;
    @Getter
    private String name;
    @Getter
    private String password;
    @Getter
    private String roleName;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean enabled;
    private Boolean credentialsNonExpired;

    public SysUserParam() {
    }

    public SysUserParam(Integer id, String username, String name, String password, String roleName, Boolean accountNonExpired, Boolean accountNonLocked, Boolean enabled, Boolean credentialsNonExpired) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.roleName = roleName;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleName);
    }


    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
