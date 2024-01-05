package com.welovecode.todo.dto;

import  static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class HttpResponse {

	protected String timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String message;
	protected Map<?, ?> data;
}
