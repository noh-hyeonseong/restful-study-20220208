package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//@JsonIgnoreProperties(value = {"password","ssn"})   // client에게 두 변수를 제공하지 않음
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity                 //자동으로 클래스 내용을 기반으로 db테이블을 생성해줌
public class User {
    @Id                 //primary key지정
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상입니다.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요.")
    private Date joinDate;
    @ApiModelProperty(notes = "사용자의 비밀번호를 입력해 주세요.")
    private String password;
    @ApiModelProperty(notes = "사용자의 주민번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int iD, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
