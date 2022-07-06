package com.project.billyus.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

//-------------------------------------------------------------------------
// WebSocket에서 HttpSession을 가져오기 위한 class
// 출처: https://nowonbun.tistory.com/621 [명월 일지:티스토리]
//-------------------------------------------------------------------------
public class HttpSessionConfigurator extends Configurator {

	public static final String SESSION = "Session";
	public static final String CONTEXT = "Context";
	
	// EndPointConfig에 HttpSession과 HttpContext를 넣는다. 
	// Request와 Response는 웹 요청, 응답시에만 필요한 데이터이기 때문에 필요없다.
	@Override
	public void modifyHandshake(ServerEndpointConfig config, 
			HandshakeRequest request, HandshakeResponse response) {
		
		// request의 세션과 context를 넣는다.
		HttpSession session = (HttpSession) request.getHttpSession();
		ServletContext context = session.getServletContext();
		
		// EndPointConfig에 넣는다.
		config.getUserProperties().put(HttpSessionConfigurator.SESSION, session);
		config.getUserProperties().put(HttpSessionConfigurator.CONTEXT, context);
		
	}

	
	
}
