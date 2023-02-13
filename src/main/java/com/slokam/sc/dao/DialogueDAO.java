package com.slokam.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.sc.entity.Dialogue;
@Repository
public interface DialogueDAO extends JpaRepository<Dialogue, Long>{

	@Query("select d from Dialogue d join d.header h join h.script sc where sc.id=?1")
	public List<Dialogue> getDialoguesByScriptId(Long id);
	
}
