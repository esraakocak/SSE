package com.example.sse.mapper;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.model.Event;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor

public class EventMapper {
	
	
	public SseEmitter.SseEventBuilder toSseEventBuilder(Event event) {
		
		return SseEmitter.event()
				          .id(RandomStringUtils.randomAlphabetic(10))
				          .name(event.getType())
				          .data(event.getBody());
		
	}

}
