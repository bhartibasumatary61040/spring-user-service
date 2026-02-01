package com.springuserservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springuserservice.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
