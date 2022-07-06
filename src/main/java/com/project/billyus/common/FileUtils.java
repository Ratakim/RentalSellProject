package com.project.billyus.common;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.billyus.vo.ChatLogVO;
import com.project.billyus.vo.GoodsByIdVO;
import com.project.billyus.vo.GoodsImgFileVO;


@Component(value="fileUtils")
public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	// root-context.xml에 빈으로 만들어둠 
	// value="c:\\data\\upload\\" 로 설정함
	// 파일들이 저장될 폴더를 명시함
	@Resource(name="uploadPath")
	String uploadPath;
	
	//------------------------------------------------------------------------------------------
	// public List<GoodsImgFileVO> parseFileInfo
	// 아래의 Map타입 메서드와 비슷한 형식으로 상품 게시판 이미지 저장을 위해 따로 메서드를 만듦.
	//------------------------------------------------------------------------------------------
	public List<GoodsImgFileVO> parseFileInfo(String uploadPathParam, GoodsByIdVO goodsByIdVO, List<MultipartFile> file) 
			throws Exception {
		logger.info("FileUtils uploadPathParam" + uploadPathParam);
		// 상품 게시판 인덱스 번호
		int goods_code = goodsByIdVO.getGoods_code();
		// 상품 게시판 작성자
		String user_ID = goodsByIdVO.getUser_ID();
		
		GoodsImgFileVO goodsImgFileVO = new GoodsImgFileVO();
		
        // 리턴할 List<Map>을 선언 후 인스턴스 생성
        List<GoodsImgFileVO> goodsImgFileVOList = new ArrayList<GoodsImgFileVO>();
        
        try {
        	logger.info("FileUtils Try Start");
		    // for문으로 각 데이터를 쪼개 맵에 넣는다
		    for(int i = 0; i < file.size(); i++) {
		    	logger.info("FileUtils for Start");
		    	// 오리지널 파일이름
		        String origin_file_name = file.get(i).getOriginalFilename();
		        
		        // 오리지널 파일의 확장자를 분리하여 어떤 파일인지를 구분한다. file.jpg   ==>   jpg
		        // substring(시작인덱스, 마지막 인덱스) 시작에서 마지막번호 사이의 문자열을 반환함
		        // substring(시작인덱스) 				시작에서 끝번호까지의 문자열을 반환함
		        // "abcde"면 순서가  "0 1 2 3 4"
		        // substring(1, 3)   "bcd"를 반환
		        // substring(2)		 "cde"를 반환
		        // String originalFileExtension = origin_file_name.substring(origin_file_name.lastIndexOf(".") + 1);
		        
		        // 바꿀 파일 이름
		        // UUID를 활용해 랜덤으로 생성한 아이디에 오리지널 아이디를 붙인다.
		        // uuid 발급
				UUID uid = UUID.randomUUID();
				logger.info(uid.toString() + "Make UUID");
		        String stored_file_name = uid.toString() + "_" + origin_file_name;
		        
		        // 파일의 사이즈
		        Long file_size = file.get(i).getSize();
		        
		        logger.info("================== fileUtils start ==================");
		        logger.info("파일 실제 이름: "	+ origin_file_name);
		        logger.info("파일 저장 이름: "	+ stored_file_name);
		        logger.info("파일 크기: "		+ file_size);
		        logger.info("content type: "	+ file.get(i).getContentType());
		        logger.info("================== fileUtils END ==================");
		        
		    	// 업로드할 디렉토리 생성한다.
				String 	savedPath = goodsPath(uploadPathParam, user_ID, goods_code);
				logger.info("FileUtils savedPath = " + savedPath);
				File 	target = new File(uploadPathParam + savedPath, stored_file_name);
				// fileData에 파일리스트에 업로드 한 파일 데이터를 구한다.
		        byte[] fileData = file.get(i).getBytes();
				
				// 파일을 업로드하게 되면 서버의 임시 디렉토리에 업로드가 된다.
				// FileCopyUtils.copy를 사용하면 지정한 디렉토리에 저장할 수 있다. 
				// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
				FileCopyUtils.copy(fileData, target);
				
				logger.info("GoodsCode for Start");
				/* goods_code가 null이라 오류가 생김 */
	            goodsImgFileVO.setGoods_code(goods_code);
	            goodsImgFileVO.setOrigin_file_name(origin_file_name);
	            goodsImgFileVO.setStored_file_name(stored_file_name);
	            logger.info("GoodsCode for middle1");
	            
	            
	            if ( i == 0) {
	            	goodsImgFileVO.setStored_thumbNail(1);
	            } else {
	            	goodsImgFileVO.setStored_thumbNail(0);
	            }
	            
	            logger.info("GoodsCode for middle2");
	            goodsImgFileVO.setFile_size(file_size);
	            goodsImgFileVO.setDelete_check(0);
	            logger.info("GoodsCode for END");
	            
	            goodsImgFileVOList.add(goodsImgFileVO);
	            logger.info("FileUtils for End");
	            
	        } // END - for
		    logger.info("FileUtils Try End");
		    
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        return goodsImgFileVOList;
	} // END - public List<Map<String, Object>> parseFileInfo
	
	//------------------------------------------------------------------------------------------
	// public List<GoodsImgFileVO> parseFileInfo 배열
	// 아래의 Map타입 메서드와 비슷한 형식으로 상품 게시판 이미지 저장을 위해 따로 메서드를 만듦.
	//------------------------------------------------------------------------------------------
	public List<GoodsImgFileVO> parseFileInfo(String uploadPathParam, String user_ID, int goods_code, MultipartFile[] file) 
			throws Exception {
		logger.info("FileUtils uploadPathParam" + uploadPathParam);
		
        // 리턴할 List<Map>을 선언 후 인스턴스 생성
        List<GoodsImgFileVO> goodsImgFileVOList = new ArrayList<GoodsImgFileVO>();
        
        try {
        	logger.info("FileUtils Try Start");
		    // for문으로 각 데이터를 쪼개 맵에 넣는다
		    for(int i = 0; i < file.length; i++) {
		    	
		    	GoodsImgFileVO goodsImgFileVO = new GoodsImgFileVO();
		    	
		    	// 오리지널 파일이름
		        String origin_file_name = file[i].getOriginalFilename();
		        
		        // UUID 특수문자 없는 랜덤 문자를 발급해서 넣어줌
				UUID uid = UUID.randomUUID();
				logger.info("Make UUID : " + uid.toString());
		        String stored_file_name = uid.toString() + "_" + origin_file_name;
		        
		        // 파일의 사이즈
		        Long file_size = file[i].getSize();
		        
		        logger.info("================== fileUtils start ==================");
		        logger.info("파일 실제 이름: "	+ origin_file_name);
		        logger.info("파일 저장 이름: "	+ stored_file_name);
		        logger.info("파일 크기: "		+ file_size);
		        logger.info("content type: "	+ file[i].getContentType());
		        logger.info("================== fileUtils END ==================");
		        
		    	// 업로드할 디렉토리 생성한다.
				String 	savedPath = goodsPath(uploadPathParam, user_ID, goods_code);
				logger.info("FileUtils savedPath = " + savedPath);
				File 	target = new File(uploadPathParam + savedPath, stored_file_name);
				// fileData에 파일리스트에 업로드 한 파일 데이터를 구한다.
		        byte[] fileData = file[i].getBytes();
				
				// FileCopyUtils.copy를 사용하면 지정한 디렉토리에 저장할 수 있다.
				FileCopyUtils.copy(fileData, target);
				
	            goodsImgFileVO.setGoods_code(goods_code);
	            goodsImgFileVO.setOrigin_file_name(origin_file_name);
	            goodsImgFileVO.setStored_file_name(stored_file_name);
	            // 배열의 첫번째 이미지가 썸네일이 된다.
	            if (i == 0) {
	            	goodsImgFileVO.setStored_thumbNail(1);
	            } else {
	            	goodsImgFileVO.setStored_thumbNail(0);
	            }
	            goodsImgFileVO.setFile_size(file_size);

	            goodsImgFileVOList.add(goodsImgFileVO);
	        } // END - for
		    
		    logger.info("FileUtils Try End");
		// END - try
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        return goodsImgFileVOList;
        
	} // END - public List<Map<String, Object>> parseFileInfo 배열
	
	//------------------------------------------------------------------------------------------
	// public List<Map<String, Object>> parseFileInfo
	//
	// Map을 리스트로 받는 List<Map> 타입의 함수.
	// 매개변수로 map과 fileList를 만들었는데
	// fileList의 세부 정보를 분할하여 Map에 넣음
	//------------------------------------------------------------------------------------------
	public List<Map<String, Object>> parseFileInfo(Map<String, Object> map, List<MultipartFile> file) 
			throws Exception {
			
		// 게시판 인덱스 번호
		String boardIDX = String.valueOf(map.get("board_id"));
		// 작성자
        String creaID = (String) map.get("user_id");
        
        // 리턴할 List<Map>을 선언 후 인스턴스 생성
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
 
        // 
        File target = new File(uploadPath);
        if(!target.exists()) target.mkdirs();
        
        // for문으로 각 데이터를 쪼개 맵에 넣는다
        for(int i = 0; i < file.size(); i++) {
        	
        	// 오리지널 파일이름
            String originalFileName = file.get(i).getOriginalFilename();
            
            // 오리지널 파일의 확장자를 분리하여 어떤 파일인지를 구분한다. file.jpg   ==>   jpg
            // substring(시작인덱스, 마지막 인덱스) 시작에서 마지막번호 사이의 문자열을 반환함
            // substring(시작인덱스) 				시작에서 끝번호까지의 문자열을 반환함
            // "abcde"면 순서가  "0 1 2 3 4"
            // substring(1, 3)   "bcd"를 반환
            // substring(2)		 "cde"를 반환
            String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            
            // 바꿀 파일 이름
            // UUID를 활용해 랜덤으로 생성한 아이디에 오리지널 아이디를 붙인다.
            String saveFileName = UUID.randomUUID().toString().replaceAll("_", "") + originalFileExtension;
            
            // 파일의 사이즈
            Long saveFileSize = file.get(i).getSize();
            
            System.out.println("================== file start ==================");
            System.out.println("파일 실제 이름: "	+originalFileName);
            System.out.println("파일 저장 이름: "	+saveFileName);
            System.out.println("파일 크기: "		+saveFileSize);
            System.out.println("content type: "+file.get(i).getContentType());
            System.out.println("================== file   END ==================");
 
            target = new File(uploadPath, saveFileName);
            file.get(i).transferTo(target);
            
            Map<String, Object> fileInfo = new HashMap<String, Object>();
 
            fileInfo.put("BOARD_IDX", boardIDX);
            fileInfo.put("ORG_FILE_NAME", originalFileName);
            fileInfo.put("SAVE_FILE_NAME", saveFileName);
            fileInfo.put("FILE_SIZE", saveFileSize);
            fileInfo.put("CREA_ID", creaID);
            fileList.add(fileInfo);
            
        }
        
        return fileList;
        
	} // END - public List<Map<String, Object>> parseFileInfo
	
	//--------------------------------------------------------------------------
	// public static String uploadFile
	// 파일을 업로드하면 호출되는 메소드
	// 날짜별로 디렉토리를 만들어서 파일을 올리기로 한다.
	//--------------------------------------------------------------------------
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) 
					throws Exception {
		// uuid 발급
		UUID uid = UUID.randomUUID();

		//uuid를 추가한 파일 이름
		//파일이름이 같으면 덮어쓰기를 하게 되므로 uuid를 추가한다.
		String savedName = uid.toString() + "_" + originalName;
		
		// 업로드할 디렉토리 생성한다.
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);

		// 파일을 업로드하게 되면 서버의 임시 디렉토리에 업로드가 된다.
		// FileCopyUtils.copy를 사용하면 지정한 디렉토리에 저장할 수 있다. 
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);

		//파일의 확장자를 검사해야 이미지파일인지 아닌지를 구분할 수 있다.
		//어떤 파일은 콤마가 여러개 있기 때문에 마지막 마침표 이후가 확장자인지를 구분해야 한다.
		// 파일의 확장자 검사
		// a.jpg / aaa.bbb.ccc.jpg
		String originalFileExtension = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		//이미지 파일은 썸네일을 생성
		//MediaUtils.getMediaType(originalFileExtension)에 마지막 확장자를 넣어서 검사하면 이미지 파일인지 알 수 있다.
		if (MediaUtils.getMediaType(originalFileExtension) != null) {
			// 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else { //이미지 파일이 아니면 아이콘을 생성한다.
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	} // End - public static String uploadFile(String uploadPath, String originalName, byte[] fileData)

	
	//-----------------------------------------------------------
	// 썸네일 생성
	//-----------------------------------------------------------
	private static String makeThumbnail(String uploadPath
			, String path, String fileName) throws Exception {
		// 이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(
				new File(uploadPath + path, fileName));
		
		// 100픽셀 단위의 썸네일 생성
		// Scalr.resize : 원본 이미지보다 축소하기 위해서 사용
		// 높이를 100픽셀로 맞추면 가로 사이즈는 자동으로 맞춰진다.
		BufferedImage destImg = Scalr.resize(
				sourceImg, Scalr.Method.AUTOMATIC
				, Scalr.Mode.FIT_TO_HEIGHT, 100);

		// 썸네일의 이름
		// s_가 붙으면 썸네일, 안붙으면 원본파일
		String thumbnailName = uploadPath + path 
				+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String originalFileExtension = fileName.substring(
				fileName.lastIndexOf(".") + 1);
		// 썸네일 생성
		ImageIO.write(destImg, originalFileExtension.toUpperCase(), newFile);
		// 썸네일의 이름을 리턴함
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	} // End - private static String makeThumbnail
	
	//-----------------------------------------------------------
	// 아이콘 생성
	//-----------------------------------------------------------
	private static String makeIcon(String uploadPath
			, String path, String fileName) throws Exception {
		// 아이콘의 이름
		String iconName = uploadPath + path + File.separator 
				+ fileName;
		// 아이콘 이름을 리턴한다.
		// File.separatorChar : 디렉토리 구분자
		// 윈도우 \ , 유닉스(리눅스) /
		return iconName.substring(uploadPath.length())
				.replace(File.separatorChar, '/');
	} // End - private static String makeIcon(String uploadPath, String path, String fileName)
	
	//-----------------------------------------------------------
	// 경로를 계산하는 메소드
	// DecimalFormat("00") : 10보다 작은 경우 자리수를 맞추기 위해서
	//-----------------------------------------------------------
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator 
						+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator 
				+ new DecimalFormat("00").format(cal.get(
						Calendar.DATE));
		
		// 디렉토리 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		
		return datePath;
	} // End - private static String calcPath(String uploadPath)
	
	//-----------------------------------------------------------
	// 유저 아이디별 상품 게시판 경로를 계산하는 메소드
	//-----------------------------------------------------------
	private static String goodsPath(String uploadPath, String user_id, int goods_code) {
		  
		// File.separator는 현제 OS환경에서 쓰이는 \나 / 와같은 경로 방식을 불러옴
		String userIdPath 		= File.separator + user_id;
		String goodsCodePath 	= userIdPath + File.separator + goods_code; 
		
		// 디렉토리 생성
		makeDir(uploadPath, userIdPath, goodsCodePath);
		logger.info(goodsCodePath);
		
		return goodsCodePath;
	} // End - private static String calcPath(String uploadPath)
	
	//-----------------------------------------------------------
	// 가변 사이즈 매개 변수 마침표3개(...) ellipsis 라고 불림
	// String... 에 uploadPath
	// paths[0] 에 yearPath
	// paths[1] 에 monthPath
	// paths[2] 에 datePath 가 들어간다.
	//-----------------------------------------------------------
	private static void makeDir(String uploadPath, String... paths) {
		
		//디렉토리가 존재하면 만들지 않고 통과
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		// forEach문 사용
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) { // 디렉토리가 존재하지 않으면
				dirPath.mkdir(); // 디렉토리 생성
			}
		}
		
	} // END - private static void makeDir
	
	//----------------------------------------------------------------
	// 확장자 분리 메서드
	//----------------------------------------------------------------
	public static String mkExtension(String fileName) {
		
		// 오리지널 파일의 확장자를 분리하여 어떤 파일인지를 구분한다. file.jpg   ==>   jpg
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		return fileExtension;
	}
	
	//------------------------------------------------------------------
	// 유저간의 채팅 기록 txt파일 저장
	// 채팅이 어느 한쪽이라도 종료되면 채팅 내용을 저장해서
	//------------------------------------------------------------------
	public ChatLogVO ChatLogVOTxt(String uploadPath, String chatLog, String ...userName ) {
		
		// 디렉토리의 이름
		String userDirName = "";
		for(int i = 0; i < userName.length; i++) {
			userDirName += ( i < (userName.length - 1) ? userName[i] + "&" : userName[i] );
		}
		
		try {
			 // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
			/*
				LocalDate now = LocalDate.now();         
				연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)        
				int year 			= now.getYear();        
				String month 		= now.getMonth().toString();       
				int monthValue 		= now.getMonthValue();        
				int dayOfMonth 		= now.getDayOfMonth();        
				int dayOfYear 		= now.getDayOfYear();        
				String dayOfWeek 	= now.getDayOfWeek().toString();        
				int dayOfWeekValue 	= now.getDayOfWeek().getValue();         
				// 결과 출력        
				System.out.println(now); 									// 2021-06-17  
				System.out.println(year); 									// 2021       
				System.out.println(month + "(" + monthValue + ")"); 		// JUNE(6)    
				System.out.println(dayOfMonth); 							// 17
				System.out.println(dayOfYear); 								// 168
				System.out.println(dayOfWeek + "(" + dayOfWeekValue + ")"); // THURSDAY(4)
			 */
			
			// 현재 날짜를 기준으로 저장할 디렉토리 생성
			// File.separator는 현제 OS환경에서 쓰이는 \나 / 와같은 경로 방식을 불러옴
			// 각 날짜를 분할해서 계산한다.
			LocalDate nowDate 	= LocalDate.now();
			int year 			= nowDate.getYear();
			int month			= nowDate.getMonthValue();
			int day		 		= nowDate.getDayOfMonth();
			
			// 각각의 경로를 만들어 관리한다.
			String userIdPath 	= File.separator + userDirName;
			String yearPath 	= userIdPath + File.separator + year;
			String monthPath 	= yearPath + File.separator + month; 
			String dayPath 		= monthPath + File.separator + day;
			
			// 디렉토리 생성
			makeDir(uploadPath, userIdPath, yearPath, monthPath, dayPath);
			
			File file = new File(uploadPath + dayPath + ".txt");
			
			if (!file.exists()) {                
				file.createNewFile();
			}
			
			// 파일을 쓴다.
			FileWriter fw = new FileWriter(file);            
			BufferedWriter writer = new BufferedWriter(fw);
			 	 
			writer.write(chatLog);
			writer.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		ChatLogVO chatLogVO = new ChatLogVO();
		
		return chatLogVO;
	}
}
/*
 
 참고 사이트
 
 출처	: https://to-dy.tistory.com/95 [todyDev]
 출처  	: https://www.hanumoka.net/2018/09/06/spring-20180906-spring-file-upload/
 
*/


