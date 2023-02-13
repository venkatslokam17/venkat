package com.slokam.sc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slokam.sc.dao.CharecterDAO;
import com.slokam.sc.dao.HeaderDAO;
import com.slokam.sc.dao.ScriptDAO;
import com.slokam.sc.entity.Action;
import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.entity.Header;
import com.slokam.sc.entity.SCCharecter;
import com.slokam.sc.entity.Script;
import com.slokam.sc.util.ScUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;

@RestController
public class ScriptController {

	@Value("${file.location}")
	private String location;
	private Logger LOGGER = LoggerFactory.getLogger(ScriptController.class);
	@Autowired
	private CharecterDAO charDao;
	
	@Autowired
	private HeaderDAO headerDao;
	
	@Autowired
	private ScriptDAO scriptDAO;
	
	@PostMapping("uploadScript") 
	@Operation(parameters = { @Parameter(name = "file" , description = "This is used to upload .docx file" , style = ParameterStyle.FORM)  })
	public ResponseEntity<String> uploadScript(@RequestParam("file") MultipartFile mfile)
	throws IOException{
		LOGGER.trace("Entered into uploadScript method");
		LOGGER.debug("Upload file Name is ::" + mfile.getOriginalFilename());
		LOGGER.info("UPLOADED SUCCESSFULLY "+ mfile.getOriginalFilename());
		LOGGER.warn("UPLOADED SUCCESSFULLY "+ mfile.getOriginalFilename());
		LOGGER.error("UPLOADED SUCCESSFULLY "+ mfile.getOriginalFilename());
		System.out.println(mfile.getOriginalFilename());
		System.out.println(mfile.getSize());
		if(mfile.getSize()<1000) {
			LOGGER.warn("Less than expected size ");
		}
		mfile.transferTo(new File(location+mfile.getOriginalFilename()));
        FileInputStream fis = new FileInputStream(location+mfile.getOriginalFilename());
		
		Set<SCCharecter> charecterSet = new HashSet<SCCharecter>(); 
		Script script = new Script();
		script.setName(mfile.getOriginalFilename());
		
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
				  header.setScript(script);
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
		script.setHeaderList(headerList);  
	   
	for(SCCharecter sCCharecter :charecterSet ) {
		System.out.println(sCCharecter);
	}
	charDao.saveAll(charecterSet);
		
		  System.out.println("================================="); 
		  for (Header header2  : headerList) { 
			  System.out.println("===="); 
			  System.out.println(header2); 
			 }
		 	
		
	
	
	//headerDao.saveAll(headerList);
		  scriptDAO.save(script);
		  LOGGER.trace("Exit from  uploadScript method");
		return null;
	}
}
