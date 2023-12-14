package com.metuncc.PhotoHocam.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User Details Class implemented here.
 *
 * @author bartu can palamut
 * @version 1.0.0
 * */


public class JwtUserDetails implements UserDetails {
    /**ID of User**/
    private Long id;
    /**Username of User**/
    private String username;
    /**Password of User**/
    private String password;
    /**Permissions of User**/
    private Collection<? extends GrantedAuthority> authorities;

    //Not implemented for now
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Not implemented for now
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Not implemented for now
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Not implemented for now
    @Override
    public boolean isEnabled() {
        return true;
    }

    public JwtUserDetails(Long id, String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities=authorityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
