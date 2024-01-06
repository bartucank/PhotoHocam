package com.metuncc.PhotoHocam.repository;

import com.metuncc.PhotoHocam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.id=:id")
    User getById(@Param("id") Long id);

    @Query("select u from User u where u.username=:username")
    User findByUsername(@Param("username")String username);

    @Query("select u from User u where :user in u.friends")
    List<User> getByFriend(@Param("user") User user);

    @Query("select u from User u where :user not in u.friends")
    List<User> getByUnFriend(@Param("user") User user);
}