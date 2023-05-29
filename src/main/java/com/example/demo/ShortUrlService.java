package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ShortUrlService {
    private static final int SHORT_URL_LENGTH = 6;

    private Map<String, String> originalToShortUrlMap;
    private Map<String, String> shortUrlToOriginalMap;
    private Random random;

    public ShortUrlService() {
        originalToShortUrlMap = new HashMap<>();
        shortUrlToOriginalMap = new HashMap<>();
        random = new Random();
    }

    public String generateShortUrl(String originalUrl) {
        if (originalToShortUrlMap.containsKey(originalUrl)) {
            return originalToShortUrlMap.get(originalUrl);
        }

        String shortUrl = generateRandomShortUrl(originalUrl);
        originalToShortUrlMap.put(originalUrl, shortUrl);
        shortUrlToOriginalMap.put(shortUrl, originalUrl);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return shortUrlToOriginalMap.get(shortUrl);
    }

    private String generateRandomShortUrl(String originalUrl) {
        StringBuilder sb = new StringBuilder();
        String sanitisedUrl = removeSpecialCharacterFromOriginalUrl(originalUrl);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = random.nextInt(sanitisedUrl.length());
            sb.append(sanitisedUrl.charAt(randomIndex));
        }
        return "java.to/" + sb.toString();
    }

    private String removeSpecialCharacterFromOriginalUrl(String originalUrl) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < originalUrl.length(); i++) {
            char c = originalUrl.charAt(i);
            if (isValidCharacter(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private boolean isValidCharacter(char c) {
        return Character.isLetterOrDigit(c);
    }

}
