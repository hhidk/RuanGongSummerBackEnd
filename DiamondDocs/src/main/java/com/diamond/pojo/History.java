package com.diamond.pojo;

import lombok.Data;

@Data
public class History
{
    private String docID;
    private String userID;
    private int iteration;
    private int operation;
    private String operateTime;

}
