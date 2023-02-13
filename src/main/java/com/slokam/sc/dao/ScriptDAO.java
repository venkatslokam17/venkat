package com.slokam.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.sc.entity.Script;
@Repository
public interface ScriptDAO extends JpaRepository<Script, Long> {

}
