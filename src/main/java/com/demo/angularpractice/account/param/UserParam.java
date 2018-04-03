package com.demo.angularpractice.account.param;

import com.demo.angularpractice.account.domain.BaseReqParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户
 *
 * @author dzy
 */
@Setter
public class UserParam extends BaseReqParam implements UserDetails {
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
