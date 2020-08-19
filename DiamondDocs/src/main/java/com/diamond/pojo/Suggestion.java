package com.diamond.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Suggestion implements Serializable
{
    String id;
    String content;
}