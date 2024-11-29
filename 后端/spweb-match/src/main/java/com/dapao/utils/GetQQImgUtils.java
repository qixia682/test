package com.dapao.utils;

import org.json.JSONException;
import org.springframework.web.client.RestTemplate;

public class GetQQImgUtils {
    public static String GetQQImgUrl(String email) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.leafone.cn/api/qqnick?qq=%s".formatted(email);
        String result = restTemplate.getForObject(url, String.class);
        String urlPattern = "\"avatar\": \"(https?://[^\\s\"]+)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(urlPattern);
        java.util.regex.Matcher matcher = pattern.matcher(result);
        return matcher.find() ? matcher.group(1) : "";
    }
}

