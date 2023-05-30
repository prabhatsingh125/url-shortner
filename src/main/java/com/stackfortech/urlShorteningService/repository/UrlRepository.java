package com.stackfortech.urlShorteningService.repository;

import com.stackfortech.urlShorteningService.entity.UrlShortnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlShortnerEntity,Long>
{
    public UrlShortnerEntity findByShortLink(String shortLink);
}
