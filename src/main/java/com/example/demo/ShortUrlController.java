package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/short-url")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/generate")
    public String generateShortUrl(@RequestBody String originalUrl) {
        return shortUrlService.generateShortUrl(originalUrl);
    }

    @GetMapping("/original/{shortUrl}")
    public String getOriginalUrl(@PathVariable String shortUrl) {
        return shortUrlService.getOriginalUrl(shortUrl);
    }
}