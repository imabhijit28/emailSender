package com.java.emailSender.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

	private Object results;

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

	public static ResponseEntity<Response> buildResponse(Object results, HttpStatus status) {
		Response response = new Response();
		response.setResults(results);
		return new ResponseEntity<Response>(response, status);
	}

}
