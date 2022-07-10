package com.wisdomleaf.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpeakingClockServiceTest {

	private SpeakingClockService clockService;

	@BeforeEach
	public void setup() {
		clockService = mock(SpeakingClockService.class);
	}

	@Test
	public void testValidateFormat() {

		String time = "12:65";
		clockService.validateFormat(time);
	}

	@Test
	public void testConvertTimeFromNumberToWords() {
		String time = "12:56";
		clockService.setHours(12);
		clockService.setMinutes(56);
		String actual = clockService.convertTimeFromNumberToWords(time);
	}
}
