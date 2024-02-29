package com.course.blog.handler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*
 * Blog프로젝트에서 [채팅하기] 버튼 클릭시 
 * chatapp과 연결되게 할거니까 SocketHandler구현시 
 * 딱히 handleTextMessge가 필요없지않을까..? 어차피 데이터가 오갈게 아닌데
 */
@Slf4j
public class SocketTextHandler extends TextWebSocketHandler {
	
	//WebSocketSession객체를 저장하는 Set이다. 
	//ConcurrentHashMap은 Thread-safe한 Set을 생성해준다. (ConcurrentHashMap은 나중에 따로 공부하고)
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

	//WebSocket이 연결이 성공적으로 수립되었을 때 호출되며 연결된 WebSocketSession객체를 Set에 추가 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		log.info("연결 완료!");
		
	}

	//WebSocket연결이 닫혔을 때 호출되며 닫힌 WebSocketSession객체를 Set에서 제거한다.
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		log.info("연결 끊는다?");
	}
	
	
}
