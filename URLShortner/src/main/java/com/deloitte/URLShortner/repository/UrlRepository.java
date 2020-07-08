package com.deloitte.URLShortner.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.URLShortner.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
