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
    private Long id;
    private Long sender;
    private Long receiver;

}
