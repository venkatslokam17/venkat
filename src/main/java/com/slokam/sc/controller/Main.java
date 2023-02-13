package com.slokam.sc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.slokam.sc.entity.Action;
import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.entity.Header;
import com.slokam.sc.entity.SCCharecter;
import com.slokam.sc.util.ScUtil;

public class Main {
	public static void main2(String[] args) {
		System.out.println(ScUtil.replaceSpecialCharecter("BOY (V.kjhasdf;kasddfa.)"));
		
	}
	public static void main(String[] args) throws IOException{
		FileInputStream fis = new FileInputStream("e:\\files\\sample1.docx");
		
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
				  charecter = new SCCharecter();
				  charecter.setName(text);
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
    System.out.println("=================================");
	for (Header header2 : headerList) {
		System.out.println("====");
		System.out.println(header2);
	}	
		
	}
}
