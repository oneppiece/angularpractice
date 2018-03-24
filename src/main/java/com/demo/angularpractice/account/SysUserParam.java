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

public class SysUserParam extends BaseParam implements UserDetails {
    private static final long serialVersionUID = 5274162006516373373L;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String roleName;
    @Setter
    private Boolean accountNonExpired;
    @Setter
    private Boolean accountNonLocked;
    @Setter
    private Boolean enabled;

    @Setter
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

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleName);
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
