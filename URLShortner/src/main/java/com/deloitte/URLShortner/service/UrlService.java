package com.deloitte.URLShortner.service;

import org.springframework.stereotype.Service;

import com.deloitte.URLShortner.dto.UrlRequest;

@Service
public interface UrlService {
	public String convertLongUrlToShortUrl(UrlRequest request); 
	public String getLongUrl(String shortUrl);
}
