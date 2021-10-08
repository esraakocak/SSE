package com.example.sse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.sse.model.Event;
import com.example.sse.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
	
	
	private final NotificationService notificationService;
	public static final String MEMBER_ID_HEADER = "MemberId";
	
	  @PostMapping
	    @ResponseStatus(HttpStatus.ACCEPTED)
	    public void publishEvent(@RequestHeader(name = MEMBER_ID_HEADER) String memberId, @RequestBody Event event) {
		  log.debug("Publishing event {} for member with id {}", event, memberId);
	        notificationService.sendNofication(memberId, event);
	    }

}
