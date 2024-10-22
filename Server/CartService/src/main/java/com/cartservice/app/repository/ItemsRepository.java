package com.cartservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartservice.app.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer>{

}
