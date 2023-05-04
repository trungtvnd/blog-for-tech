package com.trungtv.blogauth.service;

public interface CacheService {

    void set(String key, String value);

    void set(String key, String value, Integer timeout);

    String get(String key);

    void remove(String key);
}
