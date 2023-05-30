package com.stackfortech.urlShorteningService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponseDto
{
    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;

}
