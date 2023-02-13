package com.slokam.sc;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.slokam.sc.dao.DialogueDAO;
import com.slokam.sc.entity.Dialogue;
import com.slokam.sc.service.DialogueService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestDialogueService {

	@Mock
	private DialogueDAO dialogueDao;
	@InjectMocks
	private DialogueService service;
	@Test
	public void test() {
		Dialogue d1 = new Dialogue();
		d1.setDialogue("hello");
		
		Dialogue d2 = new Dialogue();
		d2.setDialogue("hello hello hello hello");
		Dialogue d3 = new Dialogue();
		d3.setDialogue("hello hello hello hello hello hello hello hello hello hello hello hello");
		Dialogue d4 = new Dialogue();
		d4.setDialogue("hello hi");
		
		List<Dialogue> list2 =  Arrays.asList(d1,d2,d3,d4);
		
		when(dialogueDao.getDialoguesByScriptId(1L)).thenReturn(list2);
		
		List<Dialogue> list = service.getDialoguesMoreThan20chars(1L);
		
		org.junit.Assert.assertEquals(2, list.size());
	
	}
	
	
}
