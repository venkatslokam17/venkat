package com.slokam.sc.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.sc.dao.DialogueDAO;
import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.entity.SCCharecter;

@Service
public class DialogueService {

	@Autowired
	private DialogueDAO dialogDao;
	
	public List<Dialogue> getDialogues(Long scriptid){
		
		List<Dialogue> dialogueList = dialogDao.getDialoguesByScriptId(scriptid);
		dialogueList.forEach((d)->{
			d.setHeader(null);
		});
		
		return dialogueList;
	}
	
 public List<Dialogue> getDialoguesMoreThan20chars(Long scriptid){
		
		List<Dialogue> dialogueList = dialogDao.getDialoguesByScriptId(scriptid);
		dialogueList.forEach((d)->{
			d.setHeader(null);
		});
		
		List<Dialogue> dialogues = dialogueList.stream().filter((d)->{
			if(d.getDialogue().length()>20) return true;
			else return false;
		}).collect(Collectors.toList());
		
		
		return dialogues;
	}
	
  public Map<SCCharecter, List<Dialogue>> getDialoguesVsCharec(Long scriptid){
		List<Dialogue> dialogueList = dialogDao.getDialoguesByScriptId(scriptid);
		
		dialogueList.forEach((d)->{
			d.setHeader(null);
		});
		
		Map<SCCharecter, List<Dialogue>> map =
				 dialogueList.stream().collect(Collectors.groupingBy( Dialogue :: getScCharecter ));
		
		return map;
	}
  
  public List<Dialogue>getDialogueLessThan5chars(Long scriptid){
	  List<Dialogue>dialoguelist=dialogDao.getDialoguesByScriptId(scriptid);
	  dialoguelist.forEach((d)->{d.setHeader(null);
	  });
	  List<Dialogue>dialogues=(List<Dialogue>) dialoguelist.stream().filter((d)->{
		  if(d.getDialogue().length()<5)return true;
		return false;
	  });
	  return dialogues;
  }
}
