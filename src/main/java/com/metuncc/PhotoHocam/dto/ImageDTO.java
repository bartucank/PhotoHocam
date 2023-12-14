package com.metuncc.PhotoHocam.dto;

import lombok.Getter;
import lombok.Setter;


public class ImageDTO {
    private byte[] data;

    public ImageDTO(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
