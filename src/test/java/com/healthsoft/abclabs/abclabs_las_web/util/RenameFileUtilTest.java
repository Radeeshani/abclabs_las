package com.healthsoft.abclabs.abclabs_las_web.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RenameFileUtilTest {

	@Test
	public void fileNameLengthMustBeForty() {
		RenameFileUtil renameFileUtil=RenameFileUtil.getFileUtil();
		
		String fileName = renameFileUtil.generateUniqueFileName("myPDF.pdf");
		
		assertEquals(40, fileName.length());

	}
	
	@Test
	public void fileExtensionMustBePDF() {
		RenameFileUtil renameFileUtil=RenameFileUtil.getFileUtil();
		
		String fileName = renameFileUtil.generateUniqueFileName("myPDF.pdf");
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		
		System.out.println(fileName);
		System.out.println(fileExtension);
		
		assertEquals(".pdf", fileExtension);		

	}

}
