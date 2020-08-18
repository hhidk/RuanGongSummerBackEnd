package com.diamond.pojo;

import lombok.Data;

@Data
public class Suggestion
{
    String id;
    String type;
    String authorId;
    String createdAt;
    boolean hasComments;
}