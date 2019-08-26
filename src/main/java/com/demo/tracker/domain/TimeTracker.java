package com.demo.tracker.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "TimeTracker")
public class TimeTracker {


	@Id
	private String id;
	
	@NotEmpty @Email
	private String email;

	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy HH.mm")
	private Date startTime;

	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy HH.mm")
	private Date endTime;

	public TimeTracker() {

	}

	public TimeTracker(String email, Date startTime, Date endTime) {
		super();
		this.email = email;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TimeTracker [id=").append(id).append(", email=").append(email).append(", startTime=")
				.append(startTime).append(", endTime=").append(endTime).append("]");
		return builder.toString();
	}

}
