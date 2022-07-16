package com.attra.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attra.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
