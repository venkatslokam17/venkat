package com.slokam.sc.util;

import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.slokam.sc.entity.Header;
import com.slokam.sc.entity.SCCharecter;

public class ScUtil {

	public static boolean isCharecter(String name ) {
		if (name == null || name.trim()=="") {
			return false;
		}
		boolean flag =true;
		for(int i= 0;i<name.length();i++) {
			if( !(  ( name.charAt(i) >=65 && name.charAt(i)<=90) ||  name.charAt(i)==' ' ) ) {
				flag =false;
				break;
			}
		}
		return flag;
	}
	
	public static boolean isCharecter(XWPFParagraph para) {
		boolean flag =false;
		if(para.getIndentationLeft() >= 100 && para.getIndentationLeft() <=4060)
		{
			// (CONT'D)
			String name = para.getText();
			//name = name.replace("(CONT'D)", "").trim();
			name =  replaceSpecialCharecter(name);
			if ( !ScUtil.isHeader(name) && ScUtil.isCharecter(name) ) {
				flag =true;
			}
		}	
		return flag;
	}
	
	public static String replaceSpecialCharecter(String name) {
		
		if(name.indexOf('(') != -1) {
			name = name.substring(0,name.indexOf('(')).trim();
		}
		return name;
	}
	public static boolean isHeader(String header) {
		if(header==null || header.trim().equals("")) {
			return false;
		}
		
		header = header.trim();
		
		if( !(header.startsWith("INT") || header.startsWith("EXT")) ) {
			return false;
		}
		
		for(int i =0;i<header.length();i++) {
			
			if(header.charAt(i) >=97  && header.charAt(i)<=122) {
				return false;
			}
			
		}
		
		return true;
	}
	public static boolean isHeader(XWPFParagraph para) {
		boolean flag =false;
		if (para.getIndentationLeft() <=1180)
		{
			String text = para.getText();
			if(ScUtil.isHeader(text)) {
				flag=true;
			}
		}
		return flag;
	}

	//EXT. THE TAVERN STRATFORD- EVENING
	public static Header splitIntoHeader(String text) {
		Header header = null;
		
		text =text.trim();
		String[] values = text.split("\\.");
		if(values.length == 2) {
			header = new Header();
			header.setIntOrExt(values[0].trim());
			String values2[] =values[1].split("-");
			header.setLocation(values2[0].trim());
			if(values2.length>=2) {
			   header.setDayTime(values2[1].trim());
			}
		}
		return header;
	}
	
	public static boolean isAction(XWPFParagraph para) {
		boolean flag = false;
		
		if(para.getIndentationLeft()>=100 && para.getIndentationLeft()<=1180) {
			String text = para.getText();
			if(!text.trim().equals("") && !isHeader(text) && !isCharecter(text)) {
				flag =true;
			}
		}
		return flag;
	}
	
	public static boolean isDialogue(XWPFParagraph para) {
		boolean flag =false;
		if(para.getIndentationLeft()>=1500 && para.getIndentationLeft()<2800) {
			flag =true;
		}
		
		return flag;
		
	}
	public static String getTypeOfComp(XWPFParagraph para) {
		String type="";
		
		if(isHeader(para)){
			type="HEADER";
		} else if(isAction(para)) {
			type="ACTION";
		} else if(isDialogue(para)) {
			type="DIALOGUE";
		} else if(isCharecter(para)) {
			type="CHARECTER";
		} else {
			type="BLANK";
		}
		
		return type;
	}
	public static SCCharecter getCharecterFromChareterList(Set<SCCharecter> scCharSet, String name){
		SCCharecter scobj =null;
		boolean flag =false;
		for (SCCharecter scCharecter : scCharSet) {
			if(scCharecter.getName().equals(name)){
				scobj = scCharecter;
				flag = true;
			}
		}
		if(!flag) {
			scobj = new SCCharecter();
			scobj.setName(name);
		}
		return scobj;
	}
}
