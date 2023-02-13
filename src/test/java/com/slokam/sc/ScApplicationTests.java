package com.slokam.sc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class ScApplicationTests {

	@Test
	void testAddCalc() {
		Calc c = new Calc();
		int r = c.calc("add", 20, 30);
		assertEquals(50, r);
	}
	
	@Test
	void testSubCacl() {
		Calc c = new Calc();
		int r = c.calc("sub", 50, 30);
		assertEquals(20, r);
	}
	
	@Test
	void testMulCalc() {
		Calc c = new Calc();
		int r = c.calc("mul",10,20);
		
		assertEquals(200, r);
	}
	@Test
	void testDivCalc() {
		Calc c = new Calc();
		int r = c.calc("div",100,20);
		assertEquals(5, r);
	}
	@Test
	void testMdCalc() {
		Calc c = new Calc();
		int r = c.calc("md",100,20);
		assertEquals(0, r);
		/*
		 * if(r>0) { assertTrue(true); } else { assertTrue(false); }
		 */
		
	}
	
	
}
