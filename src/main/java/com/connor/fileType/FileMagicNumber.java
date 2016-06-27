package com.connor.fileType;

import java.io.IOException;
import java.io.InputStream;

import com.util.UtilsALL;

/**
 * 文件魔数
 * 很多类型的文件，其起始的几个字节(一般去28个字节)的内容是固定的（或是有意填充，或是本就如此）。
 * 根据这几个字节的内容就可以确定文件类型，因此这几个字节的内容被称为魔数 (magic number)
 * @author connor_zeng
 *
 */
public class FileMagicNumber {
	
	public static void main(String[] args) throws IOException {
		
		InputStream inputStream = FileMagicNumber.class.getResourceAsStream("/image/10188552.jpg");
		
		String header = getFileHeader(inputStream);
		
		FileType[] fileTypes = FileType.values();
		for (FileType fileType : fileTypes){
			if (header.startsWith(fileType.getValue())){
				System.out.println(fileType.name());
			}
		}
	}
	
	/**
	 * 获取文件头
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String getFileHeader(InputStream inputStream) throws IOException{
		byte[] headers = new byte[28]; 
		
		inputStream.read(headers, 0, headers.length);
		inputStream.close();
		return UtilsALL.bytesToHexString(headers).toUpperCase();
	}
	
}
/**
 * 枚举类,继承Enum
 * @author connor_zeng
 *
 */
enum FileType{
	
	JPEF("FFD8FF"),
	PNG("89504E47"),
	GIF("47494638");
	
	
	private String value = "";
	private FileType(){
	}
	private FileType(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
