package com.diamond.dto;

import lombok.Data;

@Data
public class HistoryPlus {
    String userID;
    String userName;
    int operation;
    String operateTime;
    String imagePath;
}
