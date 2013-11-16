package org.hibernarm.management.exception;

public class CancleOneCommitException extends RuntimeException{
       public CancleOneCommitException(){
    	   
       }
       public CancleOneCommitException(String msg){
    	   super(msg);
       }
}
