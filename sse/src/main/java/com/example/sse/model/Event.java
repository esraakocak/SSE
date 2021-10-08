package com.example.sse.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable{
	
	private String type;
	private Map<String , Object> body;
	

}
