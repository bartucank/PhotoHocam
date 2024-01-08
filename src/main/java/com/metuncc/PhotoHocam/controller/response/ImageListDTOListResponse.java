package com.metuncc.PhotoHocam.controller.response;

import com.metuncc.PhotoHocam.dto.ImageListDTO;

import java.util.List;

public class ImageListDTOListResponse {
    private List<ImageListDTO> list;

    public List<ImageListDTO> getList() {
        return list;
    }

    public void setList(List<ImageListDTO> list) {
        this.list = list;
    }
}
