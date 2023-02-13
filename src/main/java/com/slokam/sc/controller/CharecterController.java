package com.slokam.sc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.sc.dao.CharecterDAO;
import com.slokam.sc.dao.HeaderDAO;
import com.slokam.sc.entity.Action;
import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.entity.Header;
import com.slokam.sc.entity.SCCharecter;
import com.slokam.sc.util.ScUtil;



@RestController
public class CharecterController {
	@Value("${file.upload.location}")
	private String data;
	
	private Logger LOGGER =  LoggerFactory.getLogger(CharecterController.class);
	@Autowired
	private CharecterDAO charecterDAO;
	
	@Autowired
	private HeaderDAO headerDao;
	
	@Autowired
	private CharecterDAO charecterDao;
	
	@PostMapping("saveCharecter")
	public ResponseEntity<SCCharecter> saveCharecter( @RequestBody @Valid  SCCharecter sc ) {
		charecterDao.save(sc);
		return ResponseEntity.ok(sc);
	}
	
	@GetMapping("saveCharecters")
	public ResponseEntity<String> saveCharecters() throws IOException{
		// read the data from doc file
		
		FileInputStream fis = new FileInputStream("e:\\files\\allistrue_screenplay-converted.docx");
		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> paraList = document.getParagraphs();
		
		
		Set<SCCharecter> set  = new HashSet<>();
		for (XWPFParagraph xwpfParagraph : paraList) {
			if(xwpfParagraph.getIndentationLeft() >= 2800 && xwpfParagraph.getIndentationLeft() <=4060)
			{
				// (CONT'D)
				String name = xwpfParagraph.getText();
				name = name.replace("(CONT'D)", "").trim();
				if ( ScUtil.isCharecter(name) ) {
					SCCharecter charecter = new SCCharecter();
					charecter.setName(name);
					set.add(charecter);
				}
			}
		}
		LOGGER.error(set.toString());
		
		charecterDAO.saveAll(set);
		
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@GetMapping("getHeaders")
	public ResponseEntity<String> getHeaders() throws IOException{
		
		FileInputStream fis = new FileInputStream("e:\\files\\allistrue_screenplay-converted.docx");
		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> list = document.getParagraphs();
		List<Header> headerList = new ArrayList<Header>();
		
		for (XWPFParagraph xwpfParagraph : list) {
			String text = xwpfParagraph.getText();
			
			if (xwpfParagraph.getIndentationLeft() <=100)
			{
				if(ScUtil.isHeader(text)) {
					Header header =  ScUtil.splitIntoHeader(text);
					if(header !=null) {
						headerList.add(header);
					}
				}
			}
		}
		System.out.println(headerList);
		headerDao.saveAll(headerList);
		
		return new ResponseEntity<String>("Success",HttpStatus.OK) ;
		
	}
	@GetMapping("saveData")
	public String saveData() throws IOException {
		FileInputStream fis = new FileInputStream("e:\\files\\Sample1.docx");
		Set<SCCharecter> charecterSet = new HashSet<SCCharecter>(); 
		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> list = document.getParagraphs();
		int seq=1;
		Header header = null;
		SCCharecter charecter = null;
		List<Action> actionList = null;
		List<Dialogue> dialogueList = null;
		List<Header> headerList = new ArrayList<Header>();
		String text =null;
		for (XWPFParagraph xwpfParagraph : list) {
			 text = xwpfParagraph.getText();
			if(text !=null && !text.trim().equals("")) {
			  String type =	ScUtil.getTypeOfComp(xwpfParagraph);
			  System.out.println("============================");
			  System.out.println(xwpfParagraph.getIndentFromLeft());
			  System.out.println(text);
			  System.out.println(type);
			  System.out.println("============================");
			
			  if(type.equals("HEADER")) {
				  if(header !=null) {
					header.setActionList(actionList);
					header.setDialogueList(dialogueList);
				    headerList.add(header);
				  }
				  header = ScUtil.splitIntoHeader(text);
				  actionList = new ArrayList<Action>();
				  dialogueList = new ArrayList<Dialogue>();
				  seq=1;
			  }else if(type.equals("ACTION")) {
				  Action action = new Action();
				  action.setSequenceId(seq);
				  action.setHeader(header);
				  action.setDescription(text);
				  actionList.add(action);
				  seq++;
			  }else if(type.equals("CHARECTER")) {
				  text = ScUtil.replaceSpecialCharecter(text);
				  
				  charecter = ScUtil.getCharecterFromChareterList(charecterSet, text);
				  
				  charecterSet.add(charecter);
				  
			  }else if(type.equals("DIALOGUE")) {
				  Dialogue dialogue = new Dialogue();
				  dialogue.setDialogue(text);
				  dialogue.setSequenceId(seq);
				  dialogue.setScCharecter(charecter);
				  dialogue.setHeader(header);
				  dialogueList.add(dialogue);
				  seq++;
			  }
			}
		}
		
		header.setActionList(actionList);
		header.setDialogueList(dialogueList);
	    headerList.add(header);
		  
	   
	for(SCCharecter sCCharecter :charecterSet ) {
		System.out.println(sCCharecter);
	}
	 charecterDao.saveAll(charecterSet);
		
		  System.out.println("================================="); 
		  for (Header header2  : headerList) { 
			  System.out.println("===="); 
			  System.out.println(header2); 
			 }
		 	
		
	
	
	headerDao.saveAll(headerList);
		return null;
	}
}
