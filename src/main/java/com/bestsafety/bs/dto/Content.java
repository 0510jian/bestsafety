package com.bestsafety.bs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@DynamicInsert
public class Content {
    @Id
    int id;

    String title;
    String content;
    String createDate;
    String updateDate;
    int viewCount;
}
