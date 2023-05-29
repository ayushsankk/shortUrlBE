import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShortUrlGenerator {
    private static final int SHORT_URL_LENGTH = 6;

    private Map<String, String> originalToShortUrlMap;
    private Map<String, String> shortUrlToOriginalMap;
    private Random random;

    public ShortUrlGenerator() {
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
        return "ayush.to/" + sb.toString();
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


    public static void main(String[] args) {
        ShortUrlGenerator generator = new ShortUrlGenerator();

        // Generate short URLs
        String originalUrl1 = "https://www.example.com/page1";
        String shortUrl1 = generator.generateShortUrl(originalUrl1);
        System.out.println("Short URL for " + originalUrl1 + ": " + shortUrl1);

        String originalUrl2 = "https://www.example.com/page2";
        String shortUrl2 = generator.generateShortUrl(originalUrl2);
        System.out.println("Short URL for " + originalUrl2 + ": " + shortUrl2);

        // Retrieve original URLs
        String originalUrlFromShort1 = generator.getOriginalUrl(shortUrl1);
        System.out.println("Original URL for " + shortUrl1 + ": " + originalUrlFromShort1);

        String originalUrlFromShort2 = generator.getOriginalUrl(shortUrl2);
        System.out.println("Original URL for " + shortUrl2 + ": " + originalUrlFromShort2);

        // Generate short URL again for the same original URL
        String shortUrl3 = generator.generateShortUrl(originalUrl2);
        System.out.println("Short URL for " + originalUrl2 + ": " + shortUrl3 + " (previously generated)");
    }
}
