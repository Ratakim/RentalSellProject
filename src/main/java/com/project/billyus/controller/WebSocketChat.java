package com.project.billyus.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.project.billyus.common.HttpSessionConfigurator;


//----------------------------------------------------------------------------
// webSocket을 위한 Controller
// 출처: https://nowonbun.tistory.com/621 [명월 일지:티스토리]
//----------------------------------------------------------------------------
// handshake 설정하기 위한 클래스를 지정한다.
@Controller
@ServerEndpoint(value="/rentaltalk.do", configurator = HttpSessionConfigurator.class)
public class WebSocketChat {
    
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
	
	// 여러 단체방일 때 세션
	private static final List<Session> sessionList = new ArrayList<Session>();
    
    // 세션 아이디와 Http세션의 사용자 아이디를 묶을 때 사용
    private Map<Session, EndpointConfig> configs = Collections.synchronizedMap(new HashMap<>());
    
    public WebSocketChat() {
        // TODO Auto-generated constructor stub
        System.out.println("웹소켓(서버) 객체생성");
    }
    
    // 세션 오픈
    @OnOpen
    public void onOpen(Session session,  EndpointConfig config) {
        logger.info("Open session id:" + session.getId());
        
        // EndpointConfig의 클래스를 위 맵에 넣는다.
        if(!configs.containsKey(session)) {
        	
        	// EndPointConfig의 클래스를 위 맵에 넣는다.
        	// Session 클래스는 connection이 될 때마다 
        	// 인스턴스 생성되는 값이기 때문에 키로서 사용할 수 있다
        	configs.put(session, config);
        	config.getClass();
        	
        	// HttpSessionConfigurator에서 설정한 session값을 가져온다.
        	HttpSession httpSession = (HttpSession)
    		config.getUserProperties().get(HttpSessionConfigurator.SESSION);
        	
        	// 저장되어 있는 유저의 세션을 가져온다.
    		String senderSession = (String) httpSession.getAttribute("userInfoId");
    		
	        try {
	            final Basic basic = session.getBasicRemote();
	            basic.sendText(	  "<div class='connect-user'>"
			            			+ "<span>" 
			            				+ senderSession 
			            			+ "</span>님 대화방에 연결 되었습니다."
		            			+ "</div>");   
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            
	        }
        }
        sessionList.add(session);
    }
    
    /*
     * 모든 사용자에게 메시지를 전달한다.
     * @param self
     * @param sender
     * @param message
     */
    private void sendAllSessionToMessage(Session self, String sender, String message) {
    	
        try {
        	
            for(Session session : WebSocketChat.sessionList) {
            	
                if( !self.getId().equals( session.getId() ) ) {
                	
                	// 위 맵으로 부터 userSession을 키로 EndpointConfig값을 가져온다.
                	EndpointConfig config = configs.get(self);
                	
                	// HttpSessionConfigurator에서 설정한 session값을 가져온다.
                	HttpSession httpSession = (HttpSession)
            		config.getUserProperties().get(HttpSessionConfigurator.SESSION);
            		
            		// 저장되어 있는 유저의 세션을 가져온다.
            		String senderSession = (String) httpSession.getAttribute("userInfoId");
                	
                    session.getBasicRemote()
                    .sendText( 
                    			  "<div class='receiver-container'>"
                    				+ "<div class='receiver-session-name'><span>" + senderSession + "</span></div>" 
	                    			+ "<div class='receiver-msg-div'>"
		                    			+ "<span class='receiver-name'>" + sender + "</span>" 
		                    			+ " : "
		                    			+ "<span class='receiver-msg'>" + message + "</span>"
	                    			+ "</div>"
	            				+ "</div>"
                    		);
                    
                } // if - END
                
            } // for - END
            
        // try - END
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * 내가 입력하는 메세지
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
    	
    	if(configs.containsKey(session)) {
    		// 위 맵으로 부터 userSession을 키로 EndpointConfig값을 가져온다.
    		EndpointConfig config = configs.get(session);
    		
    		// HttpSessionConfigurator에서 설정한 session값을 가져온다.
    		HttpSession httpSession = (HttpSession)
    		config.getUserProperties().get(HttpSessionConfigurator.SESSION);
    		
    		// 저장되어 있는 유저의 세션을 가져온다.
    		String senderSession = (String) httpSession.getAttribute("userInfoId"); 
    		System.out.println(senderSession);
    		
    		// 메시지와 발신자를 구분한다.
	    	String sender = message.split(",")[1];
	    	message = message.split(",")[0];
	    	
	        logger.info("Message From "+ sender + " : " + message + "sessionName" + senderSession);
	        
	        try {
	            final Basic basic=session.getBasicRemote();
	            basic.sendText(
	            				  "<div class='sender-container'>"
		            				+ "<div class='sender-session-name'><span>" + senderSession + "</span></div>"
		            				+ "<div class='sender-msg-div'>"
			            				+ "<span class='sender-name'>" + sender + "</span>" 
			            				+ " : " 
			            				+ "<span class='sender-msg'>" + message + "</span>"
		            				+ "</div>"
	            				+ "</div>");
	        }catch (Exception e) {
	            // TODO: handle exception
	            System.out.println(e.getMessage());
	        }
	        sendAllSessionToMessage(session, sender, message);
    	}
    }
    
    @OnError
    public void onError(Throwable e,Session session) {
        e.printStackTrace();
    }
    
    @OnClose
    public void onClose(Session session) {
        logger.info("Session "+session.getId()+" has ended");
        if (configs.containsKey(session)) {
        	configs.remove(session);
        }
        sessionList.remove(session);
    }
}
    //
////웹소켓을 이용한 채팅 메세지 수신/발신 모듈이다.
//각 @어노테이션의 설명은 생략