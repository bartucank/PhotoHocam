
package com.metuncc.PhotoHocam.domain;
import com.metuncc.PhotoHocam.dto.UserDTO;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity for PhotoHocam project.
 * This entity belongs to users.
 *
 * @author bartu can palamut
 * @version 1.0.0
 * */

@Entity
@Table(name = "sysUser")
public class User {
    /**
     * ID. This should be unique.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;
    /**
     * Username
     * */
    private String username;
    /**
     * Password. Should be encrypted with md5 algorithm for security.
     * */
    private String password;
    /**
     * Friend list.
     * */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friends;

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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(getId());
        dto.setName(getUsername());
        return dto;
    }
}
