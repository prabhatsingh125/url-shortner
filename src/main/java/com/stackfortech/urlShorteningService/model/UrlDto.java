package com.stackfortech.urlShorteningService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto
{
    private String url;
    private String expirationDate;  //optional


}
