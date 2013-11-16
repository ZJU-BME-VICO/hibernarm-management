package org.hibernarm.management.exception;

public class OneTimeSaveException extends RuntimeException{
        public OneTimeSaveException(){
        	
        }
        public OneTimeSaveException(String msg){
        	super(msg);
        }
}
