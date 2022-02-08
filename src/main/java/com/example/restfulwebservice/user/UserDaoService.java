package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"noh", new Date()));
        users.add(new User(2,"choi", new Date()));
        users.add(new User(3,"kim", new Date()));
    }

    private static int userCount = 3;

    public User save(User user){
        if (user.getId() == null){
            user.setId(++userCount);
        }
    }

    public User findOne(int id){
        for (User user: users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
