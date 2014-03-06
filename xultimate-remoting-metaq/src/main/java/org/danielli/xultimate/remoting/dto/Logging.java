package org.danielli.xultimate.remoting.dto;

import java.io.Serializable;
import java.util.Date;

public class Logging implements Serializable {
	
	private static final long serialVersionUID = -4953915789902930882L;

	private String content;
	
	private Date date;
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
