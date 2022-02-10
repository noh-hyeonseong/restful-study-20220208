package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

//@JsonIgnoreProperties(value = {"password","ssn"})   // client에게 두 변수를 제공하지 않음
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상입니다.")
    private String name;
    @Past
    private Date joinDate;

    private String password;
    private String ssn;
}
