package com.example.sse.service;

import com.example.sse.model.Event;

public interface NotificationService {
	
	void sendNofication(String memberId, Event event);
	
	
	

}
