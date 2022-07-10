package com.wisdomleaf.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wisdomleaf.service.SpeakingClockService;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpeakingClockControllerTest {
	
	SpeakingClockController clockController;
	
	@Mock
	SpeakingClockService clockService;
	
	@BeforeEach
	public void setUp() {
		clockService = mock(SpeakingClockService.class);
	}
	
	@Test
	public void testController() {
		clockController = mock(SpeakingClockController.class);
		String timeInNumbers = "01:23";
		doNothing().when(clockService).validateFormat(anyString());
		when(clockService.convertTimeFromNumberToWords(anyString())).thenReturn("");
		ResponseEntity<String> actual = clockController.convertTimeFromNumberToWords(timeInNumbers);
	}

}
