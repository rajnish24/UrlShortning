package com.deloitte.URLShortner.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.deloitte.URLShortner.dto.UrlRequest;
import com.deloitte.URLShortner.entity.Url;
import com.deloitte.URLShortner.repository.UrlRepository;
import com.deloitte.URLShortner.service.impl.UrlServiceImpl;
import com.deloitte.URLShortner.utils.BaseConversionUtil;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    UrlRepository mockUrlRepository;

    @Mock
    BaseConversionUtil mockBaseConversion;

    @InjectMocks
    UrlServiceImpl urlService;

    @Test
    public void convertToShortUrlTest() {
        Url url = new Url();
        url.setLongUrl("https://www.youtube.com/watch?v=bATUW8G2ONE");
        url.setCreatedDate(new Date());
        url.setId(1000);

        when(mockUrlRepository.save(any(Url.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("cLs");

        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setLongUrl("https://www.youtube.com/watch?v=bATUW8G2ONE");
        urlService.baseUrl="http://localhost:8080/api/v1/";

        assertEquals("http://localhost:8080/api/v1/cLs", urlService.convertLongUrlToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversion.decode("cLs")).thenReturn((long) 1000);

        Url url = new Url();
        url.setLongUrl("https://www.youtube.com/watch?v=bATUW8G2ONE");
        url.setCreatedDate(new Date());
        url.setId(1000);

        when(mockUrlRepository.findById((long) 1000)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://www.youtube.com/watch?v=bATUW8G2ONE", urlService.getLongUrl("cLs"));

    }
}
