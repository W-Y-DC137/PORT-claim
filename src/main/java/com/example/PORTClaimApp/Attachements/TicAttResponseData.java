package com.example.PORTClaimApp.Attachements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicAttResponseData {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
