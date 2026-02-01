package com.springuserservice.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public record APIError (LocalDateTime timestamp, int status, String errorMessage, 
		String message, String path){

}
