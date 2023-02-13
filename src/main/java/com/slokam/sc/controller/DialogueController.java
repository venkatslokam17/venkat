package com.slokam.sc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.entity.SCCharecter;
import com.slokam.sc.service.DialogueService;


@RestController()
@RequestMapping("dialogue")
public class DialogueController {

	@Autowired
	private DialogueService dialogueService;
	
	@GetMapping("/byScriptId/{scriptid}")
	public List<Dialogue> getDialogs(@PathVariable("scriptid") Long scriptid){
		
		
		List<Dialogue> dialogues = dialogueService.getDialogues(scriptid);
		
		return dialogues;
	}
	@GetMapping("/vsChar/{scriptid}")
	public ResponseEntity< Map<SCCharecter, List<Dialogue>> > getDialoguesVsCharec(@PathVariable("scriptid") Long scriptid){
		
		Map<SCCharecter, List<Dialogue>> map = dialogueService.getDialoguesVsCharec(scriptid);
		
		return ResponseEntity.ok(map);
	}
}
