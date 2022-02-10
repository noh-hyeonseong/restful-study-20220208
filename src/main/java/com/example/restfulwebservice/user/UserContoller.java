package com.example.restfulwebservice.user;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserContoller {
    private UserDaoService service;

    public UserContoller(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //HATEOAS
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());    //본 클래스의 "retrieveAllUsers()" uri를 가져옴
        resource.add(linkTo.withRel("all-users"));  // "all-users"라는 문구를 통하여 제공

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){     // @RequestBodyform 으로 json 등을 받기 위해 선언
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // URI로 반환하기 위하여 생성
                .path("/{id}")                                          // path에 /{id} 값을 추가
                .buildAndExpand(savedUser.getId())                      // {id}에 savedUser.getId() 값을 가져옴
                .toUri();                                               // URI로 변환

        return ResponseEntity.created(location).build() ;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
