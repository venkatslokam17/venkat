package com.slokam.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.sc.entity.Header;

@Repository
public interface HeaderDAO extends JpaRepository<Header, Long> {

}
