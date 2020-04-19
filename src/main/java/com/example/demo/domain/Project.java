package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Builder // builder를 사용할수 있게 합니다.
@Entity // jpa entity임을 알립니다.
@Getter // user 필드값의 getter를 자동으로 생성합니다.
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Setter
    private String userId;

    @Column
    @Setter
    private String title;

    @Column
    @Setter
    private String gitUrl;

    @Column
    @Setter
    private String description;

    @Lob
    @Column
    private byte[] projectImg;
}
