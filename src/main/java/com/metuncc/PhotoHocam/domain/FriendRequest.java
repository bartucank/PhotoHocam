package com.metuncc.PhotoHocam.domain;

import lombok.Data;

import javax.persistence.*;
/**
 * FriendRequest entity for PhotoHocam project.
 * This entity belongs to friend requests.
 *
 * @author doğukan akdağ
 * @version 1.0.0
 * */
@Data
@Entity
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    /**
     * id of friend request. unique
     */
    private Long id;
    /**
     * id of the user who sends the friend request
     */
    private Long sender;
    /**
     * id of the user who the friend request is sent to
     */
    private Long receiver;

}
