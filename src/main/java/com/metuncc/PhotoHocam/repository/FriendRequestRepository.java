package com.metuncc.PhotoHocam.repository;

import com.metuncc.PhotoHocam.domain.FriendRequest;
import com.metuncc.PhotoHocam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>{
    @Query("select u from FriendRequest u where u.id=:id")
    FriendRequest getById(@Param("id") Long id);

    @Query("select u from FriendRequest u where u.receiver=:receiver")
    List<FriendRequest> findByReceiver(@Param("receiver")Long receiver);

    @Query("select u from FriendRequest u where u.sender=:sender")
    List<FriendRequest> findBySender(@Param("sender")String sender);
}

