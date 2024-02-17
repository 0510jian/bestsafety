package com.bestsafety.bs.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Content {
    @Id
    int id;

    String title;
    String content;
    Date createDate;
    Date updateDate;
    int viewCount;
}
