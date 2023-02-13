package com.slokam.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.sc.entity.SCCharecter;

@Repository
public interface CharecterDAO extends JpaRepository<SCCharecter, Long> {

}
