package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensi.smartlocker.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
