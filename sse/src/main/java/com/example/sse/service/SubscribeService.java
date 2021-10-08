package com.example.sse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.repository.SubscribeRepository;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubscribeService {

	private final long eventsTimeout ;
	private final SubscribeRepository repository;
	
	public SubscribeService(@Value("${events.connection.timeout}") long eventsTimeout, SubscribeRepository repository) {
               this.eventsTimeout = eventsTimeout;
               this.repository = repository;
}
	
	public SseEmitter createEmitter (String memberId) {
		   
		SseEmitter emitter = new SseEmitter(eventsTimeout);
		//İstemci baglantısı tamamlandıgında, istemciye özel üyenin kaydını siler .
		emitter.onCompletion(() -> repository.remove(memberId));
		
		//istemci baglantı zaman asımında istemciye özel üyeyi siler
	     emitter.onTimeout(()->repository.remove(memberId));
		
		emitter.onError(e -> {
			log.error("Create Sse Emitter exception",e);
			repository.remove(memberId);
		});
		
		repository.addOrReplace(memberId, emitter);
		return emitter ;
	
	}	
}
