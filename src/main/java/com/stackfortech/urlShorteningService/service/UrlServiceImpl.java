package com.stackfortech.urlShorteningService.service;

import com.google.common.hash.Hashing;
import com.stackfortech.urlShorteningService.entity.UrlShortnerEntity;
import com.stackfortech.urlShorteningService.model.UrlDto;
import com.stackfortech.urlShorteningService.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public UrlShortnerEntity generateShortLink(UrlDto urlDto) {

        if(StringUtils.isNotEmpty(urlDto.getUrl()))
        {
            String encodedUrl = encodeUrl(urlDto.getUrl());
            UrlShortnerEntity urlToPersist = new UrlShortnerEntity();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(urlDto.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            urlToPersist.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(),urlToPersist.getCreationDate()));
            UrlShortnerEntity urlToRet = persistShortLink(urlToPersist);

            if(urlToRet != null)
                return urlToRet;

            return null;
        }
        return null;
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate)
    {
        if(StringUtils.isBlank(expirationDate))
        {
            return creationDate.plusDays(180);
        }
        LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
        return expirationDateToRet;
    }

    private String encodeUrl(String url)
    {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }

    @Override
    public UrlShortnerEntity persistShortLink(UrlShortnerEntity url) {
        UrlShortnerEntity urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    @Cacheable(key = "#url", value = "UrlShortnerEntity")
    public UrlShortnerEntity getEncodedUrl(String url) {
        UrlShortnerEntity urlToRet = urlRepository.findByShortLink(url);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(UrlShortnerEntity url) {

        urlRepository.delete(url);
    }
}
