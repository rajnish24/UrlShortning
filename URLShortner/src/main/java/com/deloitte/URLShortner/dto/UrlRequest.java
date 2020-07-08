package com.deloitte.URLShortner.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UrlRequest {
	private String longUrl;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date expiresDate;
    
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public Date getExpiresDate() {
		return expiresDate;
	}
	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}
    
    
    
    
}
