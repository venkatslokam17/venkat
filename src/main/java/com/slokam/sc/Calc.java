package com.slokam.sc;

public class Calc {

	 public int calc(String type, int a , int b) {
		 int c = 0;

		 switch (type) {
		 case  "add" : c = a+b; break;
		 case  "sub" : c =  a-b; break;
		 case  "mul" : c = a*b ; break;
		 case  "div" : c = a/b ; break;
		 case  "md"  : c = a%b ; break;
		 }
		 return c;
	 }
}
