package com.metuncc.PhotoHocam.dto;


import lombok.Data;

public class UserDTO {
   private String name;
   private Long id;
   private Long friendRequestId;

   public Long getFriendRequestId() {
      return friendRequestId;
   }

   public void setFriendRequestId(Long friendRequestId) {
      this.friendRequestId = friendRequestId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
