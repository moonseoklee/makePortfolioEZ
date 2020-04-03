package com.example.demo.domain;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

    User save(User user);
}
