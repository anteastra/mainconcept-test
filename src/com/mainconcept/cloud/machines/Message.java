package com.mainconcept.cloud.machines;

import java.io.Serializable;

import com.mainconcept.cloud.Task;

public class Message implements Serializable{

	private static final long serialVersionUID = -5721692072625517620L;
		
	private String message;
	private MessageType type;
	private Task task;
	
	public Message(MessageType type, String message, Task task) {
		
		if (type == null) {
			throw new IllegalArgumentException("message type cannot be null");
		}
		
		this.message = message;
		this.type = type;
		this.task = task;
	}
	
	public String getMessage() {
		return message;
	}
	
	public MessageType getMessageType() {
		return type;
	}
	
	public Task getTask() {
		return task;
	}
	
	public enum MessageType {
		REQUEST_PERFORM_TASK,
		REQUEST_PERFORM_SHUTDOWN,
		TASK_FREE,
		TASK_START,
		TASK_BUSY,
		TASK_DONE,
		TASK_ERROR,
		SHUTDOWNED,
		MESSAGE,
		BAD_REQUEST
	}
}
