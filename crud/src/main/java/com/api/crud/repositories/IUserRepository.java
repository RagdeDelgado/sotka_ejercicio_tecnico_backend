package com.api.crud.repositories;

import com.api.crud.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

}
