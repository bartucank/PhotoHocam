package com.metuncc.PhotoHocam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private byte[] data;

    public ImageDTO(byte[] data) {
        this.data = data;
    }
}
