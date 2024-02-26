package com.bestsafety.bs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@DynamicInsert
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="title")
    String title;
    @Column(name = "content")
    String content;

    String createDate;
    String updateDate;
    int viewCount;

    boolean notice;
}
