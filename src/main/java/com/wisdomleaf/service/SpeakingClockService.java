package com.wisdomleaf.service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpeakingClockService {

	private static final Logger log = LoggerFactory.getLogger(SpeakingClockService.class);

	private int hours;
	private int minutes;
	private String result;

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private static final String[] ONES = { "zero ", "one ", "two ", "three ", "four ", "five ", "six ", "seven ",
			"eight ", "nine ", "ten " };

	private static final String[] TEN = { "", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ",
			"seventeen ", "eighteen ", "nineteen " };

	private static final String[] TENS = { "", "ten", "twenty ", "thirty ", "forty ", "fifty " };

	/***
	 * Validates if the input is in 24 hour format.
	 * 
	 * @param timeInNumbers
	 * @throws DateTimeParseException
	 */
	public void validateFormat(String timeInNumbers) throws DateTimeParseException {
		try {
			LocalTime lt = LocalTime.parse(timeInNumbers);
			this.setHours(lt.getHour());
			this.setMinutes(lt.getMinute());
			log.info("Hour: " + this.getHours() + ", Minutes: " + this.getMinutes());
		} catch (DateTimeParseException ex) {
			throw new DateTimeParseException("Please enter time in a 24 hour format!", timeInNumbers, 1);
		}

	}

	/***
	 * Converts time in number to words.
	 */
	public String convertTimeFromNumberToWords(String timeInNumbers) {
		int hours = this.getHours();
		int minutes = this.getMinutes();
		StringBuilder result = new StringBuilder();

		if (minutes == 0) {
			if (hours == 0)
				result.append("It's Midnight");
			else if (hours == 12)
				result.append("It's Midday");
			else {
				result.append("It's ").append(getHour(hours));
			}
		} else if (minutes % 10 == 0) {
			result.append("It's " + getHour(hours) + TENS[minutes / 10]);
		} else {
			if (minutes / 10 == 0)
				result.append("It's " + getHour(hours) + ONES[minutes % 10]);
			else if (minutes / 10 == 1)
				result.append("It's " + getHour(hours) + TEN[minutes % 10]);
			else if (minutes / 10 > 1)
				result.append("It's " + getHour(hours) + TENS[minutes / 10] + " " + ONES[minutes % 10]);
		}
		log.info("Converted " + timeInNumbers + " to " + result.toString());
		return result.toString();
	}

	/***
	 * Return the hours in words.
	 * 
	 * @param hours
	 * @return
	 */
	private String getHour(int hours) {
		String hour = "";
		if (hours / 10 == 0) {
			hour = ONES[hours % 10];
		} else if (hours / 10 > 1) {
			hour = hours % 10 == 0 ? TENS[hours / 10] : TENS[hours / 10] + " " + ONES[hours % 10];
		} else
			hour = hours == 10 ? ONES[hours] : TEN[hours % 10];
		return hour;
	}

}
