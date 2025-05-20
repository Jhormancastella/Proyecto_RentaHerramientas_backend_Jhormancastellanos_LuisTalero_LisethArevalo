package com.appsecurity.app_security.persistence.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.appsecurity.app_security.exception.RateLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class RateLimitingAspect {
    private static final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private static final int REQUEST_LIMIT = 10;
    private static final long TIME_LIMIT = 60000; // 1 minute

    private final HttpServletRequest request;

    public RateLimitingAspect(HttpServletRequest request) {
        this.request = request;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::resetRequestCounts, TIME_LIMIT, TIME_LIMIT, TimeUnit.MILLISECONDS);
    }

    @Before("@annotation(RateLimited)")
    public void beforeRequest() {
        String clientIp = getClientIp();
        AtomicInteger count = requestCounts.computeIfAbsent(clientIp, k -> new AtomicInteger(0));
        if (count.incrementAndGet() > REQUEST_LIMIT) {
            throw new RateLimitExceededException("Rate limit exceeded for IP: " + clientIp);
        }
    }

    private void resetRequestCounts() {
        requestCounts.clear();
    }

    private String getClientIp() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        } else {
            ipAddress = ipAddress.split(",")[0]; // Get the first IP in case of multiple proxies
        }
        return ipAddress;
    }
}
