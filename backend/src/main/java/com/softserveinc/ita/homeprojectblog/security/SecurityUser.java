package com.softserveinc.ita.homeprojectblog.security;

import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser
        implements UserDetails
{
    private final String username;

    private final String password;

    private final List<SimpleGrantedAuthority> authorities;

    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(UserEntity user){
        // TODO move to util code
        var role = new RoleEntity();
        role.setName(RoleEntity.NameEnum.values()[(int) user.getRoleByte()]);
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                role.getName().getAuthorities()
        );
    }

}
