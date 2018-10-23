package com.connor.basic.jdk8;

import java.io.File;
import java.io.FileFilter;

public class TestFile {

	public static void main(String[] args) {

		File[] hiddenFiles = getHiddenFiles();
		for (File file : hiddenFiles) {
			System.out.println(file.getName());
		}
		
		hiddenFiles = getHiddenFilesJDK8();
		
		for (File file : hiddenFiles) {
			System.out.println(file.getName());
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	private static File[] getHiddenFiles() {
		return new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isHidden()) {
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * 使用JDK8 
	 * 行为参数传递代码
	 * 行为参数传递代码
	 * 行为参数传递代码
	 * @return
	 */
	private static File[] getHiddenFilesJDK8() {
		return new File(".").listFiles(File :: isHidden);
	}

}
