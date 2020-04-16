package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder // builder를 사용할수 있게 합니다.
@Entity // jpa entity임을 알립니다.
@Getter // user 필드값의 getter를 자동으로 생성합니다.
@NoArgsConstructor // 인자없는 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "User") // 'user' 테이블과 매핑됨을 명시
public class User {


    @Id
    @Column(nullable = false, unique = true, length = 30) // uid column을 명시합니다. 필수이고 유니크한 필드이며 길이는 30입니다.
    private String id;



    @Column(nullable = false, length = 100) // name column을 명시합니다. 필수이고 길이는 100입니다.
    private String email;

    @Transient
    @Setter
    private List<Project> projects;

    @Column
    @Setter
    private  String name;

    @Column
    @Setter
    private  String description;

    @ElementCollection
    @Setter
    private List<String> PLS = new ArrayList<String>();

    @ElementCollection
    @Setter
    private List<String> dbs = new ArrayList<String>();

    @ElementCollection
    @Setter
    private List<String> frameworks = new ArrayList<String>();

    @ElementCollection
    @Setter
    private List<String> etcs = new ArrayList<String>();

}

