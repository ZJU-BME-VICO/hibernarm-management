package org.hibernarm.management.exception;

public class UploadedFileException extends RuntimeException{
	public UploadedFileException(){
		
	}
	public UploadedFileException(String msg){
		super("message");
	}

}
