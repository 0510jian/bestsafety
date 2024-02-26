package com.bestsafety.bs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@DynamicInsert
@DynamicUpdate
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="originalName")
    String originalName;
    @Column(name="saveName")
    String saveName;
    @Column(name="extension")
    String extension;
    @Column(name = "filesize")
    long filesize;
    @Column(name = "location")
    String location;

    String createDate;

    @Column(name = "contentId")
    int contentId;
}
