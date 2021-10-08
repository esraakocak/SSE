package com.example.sse.repository;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InMemoryRepository implements SubscribeRepository{

	  private Map<String, SseEmitter> userEmitterMap = new ConcurrentHashMap<>();
	
	@Override
	public void addOrReplace(String memberId, SseEmitter emitter) {
		userEmitterMap.put(memberId, emitter);
		
	}

	@Override
	public void remove(String memberId) {
		if(userEmitterMap !=null && userEmitterMap.containsKey(memberId)) {
			log.debug("Removing subscription  for member  :{}",memberId);
			userEmitterMap.remove(memberId);
		}else {
			log.debug("No subscription to remove for member : {}",memberId);
		}
		
		
	}

	@Override
	public Optional<SseEmitter> get(String memberId) {
	
		return Optional.ofNullable(userEmitterMap.get(memberId));
	}

}
