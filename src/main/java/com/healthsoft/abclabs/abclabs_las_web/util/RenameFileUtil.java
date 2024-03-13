package com.healthsoft.abclabs.abclabs_las_web.util;

import java.util.UUID;

import javax.servlet.http.Part;

public class RenameFileUtil {
	private static RenameFileUtil renameFileUtil;
	
	private RenameFileUtil() {
		
	}
	
	public static RenameFileUtil getFileUtil() {
		if(renameFileUtil==null) {
			renameFileUtil=new RenameFileUtil();
		}
		return renameFileUtil;
	}

	public String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public String generateUniqueFileName(String originalFileName) {
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
		return uniqueFileName;
	}
}
