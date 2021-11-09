package com.babbleverse.user;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long > {
    Optional<User> findByname(String name);
}