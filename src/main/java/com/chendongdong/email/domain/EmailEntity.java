package com.chendongdong.email.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailEntity {
    private String subject;
    private String content;
    private String receiver;
}
