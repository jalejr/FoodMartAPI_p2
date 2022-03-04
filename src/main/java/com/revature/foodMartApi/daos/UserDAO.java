package com.revature.foodMartApi.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.foodMartApi.models.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer>{

}
