package com.example.sse.service;

import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.sse.mapper.EventMapper;
import com.example.sse.model.Event;
import com.example.sse.repository.SubscribeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class SseNotificationService implements NotificationService{

	
	private final SubscribeRepository subscribeRepository;
    private final EventMapper eventMapper;
    
   
    private void doSendNotification(String memberId, Event event) {
        subscribeRepository.get(memberId).ifPresentOrElse(sseEmitter -> {
            try {
                log.debug("Sending event: {} for member: {}", event, memberId);
                sseEmitter.send(eventMapper.toSseEventBuilder(event));
            } catch (IOException  e) {
                log.debug("Error while sending event: {} for member: {} - exception: {}", event, memberId, e);
                subscribeRepository.remove(memberId);
            }
        }, () -> log.debug("No emitter for member {}", memberId));
    }
    
    
    
    
	@Override
	public void sendNofication(String memberId, Event event) {
	     if(event == null) {
	    	 log.debug("there is no server event to send to device");
	    	 return;
	     }
	     
	     doSendNotification(memberId, event);
    }
	}
	
	
