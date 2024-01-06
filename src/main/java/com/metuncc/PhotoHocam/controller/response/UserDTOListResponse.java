package com.metuncc.PhotoHocam.controller.response;


import com.metuncc.PhotoHocam.dto.ImageListDTO;
import com.metuncc.PhotoHocam.dto.UserDTO;

import java.util.List;


public class UserDTOListResponse {
    private List<UserDTO> userDTOList;

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }
}
