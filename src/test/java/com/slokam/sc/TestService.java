package com.slokam.sc;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
public class TestService {


	@Mock
	private DialogueDAO dao;
	
	@InjectMocks
	private DialogueService service ;
	

	@Test
	public void test() {
		
		Dialogue d1 = new Dialogue();
		d1.setDialogue("Helllo");
		d1.setId(1L);
		d1.setSequenceId(23);
		List<Dialogue> list = new ArrayList<>();
		list.add(d1);
		List<Dialogue> dialogues = service.getDialogues(1L);
		when(dao.getDialoguesByScriptId(1L)).thenReturn(dialogues);
		List<Dialogue> l = service.getDialogues(1L);
		System.out.println(l);
		
	}
	
}
