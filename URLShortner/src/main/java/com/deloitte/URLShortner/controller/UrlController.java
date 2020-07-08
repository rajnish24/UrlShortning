package com.deloitte.URLShortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deloitte.URLShortner.dto.UrlRequest;
import com.deloitte.URLShortner.service.UrlService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    UrlService urlService;

    

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String convertToShortUrl(@RequestBody UrlRequest request) {
        return urlService.convertLongUrlToShortUrl(request);
    }
    @RequestMapping(value = "/{shortUrl}",method = RequestMethod.GET)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
