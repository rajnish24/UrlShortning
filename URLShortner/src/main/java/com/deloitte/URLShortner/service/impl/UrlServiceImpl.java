package com.deloitte.URLShortner.service.impl;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.URLShortner.dto.UrlRequest;
import com.deloitte.URLShortner.entity.Url;
import com.deloitte.URLShortner.repository.UrlRepository;
import com.deloitte.URLShortner.service.UrlService;
import com.deloitte.URLShortner.utils.BaseConversionUtil;
@Service
public class UrlServiceImpl implements UrlService{
	
	@Value("${base.Url}")
	public String baseUrl;
	@Autowired
	private UrlRepository urlRepository;
	@Autowired
    private BaseConversionUtil conversion;

	@Override
	public String convertLongUrlToShortUrl(UrlRequest request) {
		   Url url = new Url();
	        url.setLongUrl(request.getLongUrl());
	        url.setExpiresDate(request.getExpiresDate());
	        url.setCreatedDate(new Date());
	        Url entity = urlRepository.save(url);
	        
	        String shortUrl=baseUrl+conversion.encode(entity.getId());

	        return shortUrl;
	}

	@Override
	public String getLongUrl(String shortUrl) {
		long id = conversion.decode(shortUrl);
        Url entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invalid "+shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
	}

	
}
