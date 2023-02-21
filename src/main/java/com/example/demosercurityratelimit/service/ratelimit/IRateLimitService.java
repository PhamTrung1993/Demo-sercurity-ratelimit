package com.example.demosercurityratelimit.service.ratelimit;

import io.github.bucket4j.Bucket;

public interface IRateLimitService {
    Bucket resolveBucket(Long userId);

    Bucket newBucket(Long userId);
}
