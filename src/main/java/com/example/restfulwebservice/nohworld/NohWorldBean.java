package com.example.restfulwebservice.nohworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // getter setter 등을 대체함
@AllArgsConstructor         // lombok 의 기능으로 모든 변수를 활용한 생성자를 만들겠다는 어노테이션
@NoArgsConstructor          //lombok 의 기능으로 디폴트 생성자를 만들겠다는 어노테이션
public class NohWorldBean {

    private String message;

}
