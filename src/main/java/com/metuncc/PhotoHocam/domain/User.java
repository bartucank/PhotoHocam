
package com.metuncc.PhotoHocam.domain;
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
@Data
@Entity
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
    @ManyToMany
    private List<User> friends;


    public JwtUserDetails toJwtUserDetails(){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(getId(), getUsername(), getPassword(), authorityList);
    }

}
