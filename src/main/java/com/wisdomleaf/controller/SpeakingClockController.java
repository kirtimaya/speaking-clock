package com.wisdomleaf.controller;

import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.service.SpeakingClockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/speak")
@Slf4j
public class SpeakingClockController {

	private static final Logger log = LoggerFactory.getLogger(SpeakingClockController.class);

	@Autowired
	private SpeakingClockService speakingClockService;

	@GetMapping("/{timeInNumbers}")
	public ResponseEntity<String> convertTimeFromNumberToWords(@PathVariable("timeInNumbers") String timeInNumbers)  {
		log.info("Received input time in numbers!");
		try {
			speakingClockService.validateFormat(timeInNumbers);
		} catch (DateTimeParseException ex) {
			log.info("Entering Exception due to invalid format");
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(speakingClockService.convertTimeFromNumberToWords(timeInNumbers));
	}

}
