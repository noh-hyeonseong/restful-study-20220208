package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){     // @RequestBodyform 으로 json 등을 받기 위해 선언
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // URI로 반환하기 위하여 생성
                .path("/{id}")                                          // path에 /{id} 값을 추가
                .buildAndExpand(savedUser.getId())                      // {id}에 savedUser.getId() 값을 가져옴
                .toUri();                                               // URI로 변환

        return ResponseEntity.created(location).build() ;
    }
}
