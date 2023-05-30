package com.stackfortech.urlShorteningService.service;

import com.stackfortech.urlShorteningService.entity.UrlShortnerEntity;
import com.stackfortech.urlShorteningService.model.UrlDto;
import org.springframework.stereotype.Service;

@Service
public interface UrlService
{
    public UrlShortnerEntity generateShortLink(UrlDto urlDto);
    public UrlShortnerEntity persistShortLink(UrlShortnerEntity url);
    public UrlShortnerEntity getEncodedUrl(String url);
    public  void  deleteShortLink(UrlShortnerEntity url);
}
