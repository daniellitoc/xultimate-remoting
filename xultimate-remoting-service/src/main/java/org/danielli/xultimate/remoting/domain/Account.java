package org.danielli.xultimate.remoting.domain;

import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = 2718159871145819262L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
