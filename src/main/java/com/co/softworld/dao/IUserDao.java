package com.co.softworld.dao;

import com.co.softworld.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserDao extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
}
