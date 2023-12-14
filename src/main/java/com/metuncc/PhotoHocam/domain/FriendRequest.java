package com.metuncc.PhotoHocam.domain;

import lombok.Data;

import javax.persistence.*;
/**
 * FriendRequest entity for PhotoHocam project.
 * This entity belongs to friend requests.
 *
 * @author dogukan akdag
 * @version 1.0.0
 * */

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }
}
