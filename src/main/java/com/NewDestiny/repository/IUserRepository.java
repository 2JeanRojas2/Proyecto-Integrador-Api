package com.NewDestiny.repository;

import com.NewDestiny.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    UserEntity findByEmail(String email);
}