package com.kbstar.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
//Spring Framework에서 제공하는 MultipartFile 클래스를 사용하여 파일 업로드를 처리 >> Item(DTO)에 콘스트럭터 추가 & ItemController에서 활용
public class FileUploadUtil {
	public static void saveFile(MultipartFile mf, String dir) {   //파일 덩어리와, 디렉토리를 주면 저장해주는 함수
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo = 
					new FileOutputStream(dir+imgname);
			fo.write(data);
			fo.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}




