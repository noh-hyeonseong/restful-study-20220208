package com.example.restfulwebservice.nohworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController                     // @Controller + @ResponseBody 역할
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //GET
    // /noh-world (endpoint)
    @GetMapping(path="/noh-world")
    public String nohWorld() {
        return "I'm 노반장";
    }

    //GET
    // @bean을 활용한 api
    @GetMapping(path="/noh-world-bean")
    public NohWorldBean NohWorldBean() {
        return new NohWorldBean("I'm 노반장");
    }

    //GET
    //
    @GetMapping(path="/noh-world-bean/path-variable/{id}")
    public NohWorldBean NohWorldBean(@PathVariable(value = "id") String name) {       //@PathVariable 을 설정한 매개변수를 url path의 변수{name}으로 연결해줌. 만약 id->name변경시 (value = "id") 생략가능
        return new NohWorldBean(String.format("I'm 노반장, %s", name));
    }

    @GetMapping(path = "/noh-world-internationalized")
    public String nohWorldInternationalized(
            @RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
