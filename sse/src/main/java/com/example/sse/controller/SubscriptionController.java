package com.example.sse.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.service.SubscribeService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor


public class SubscriptionController {
	
	 public static final String MEMBER_ID_HEADER ="MemberId";
	
	 private final SubscribeService subscribeService;
	 
	 
	 @GetMapping("/")
	 public SseEmitter subscribeToEvents (@RequestHeader(name = MEMBER_ID_HEADER) String memberId) {
		  log.debug("Subscribing member with id {}", memberId);
		  
	       
		  return subscribeService.createEmitter(memberId);
	 }
	
	

}
