package com.example.demosercurityratelimit.service.ratelimit;

import com.example.demosercurityratelimit.model.Plan;
import com.example.demosercurityratelimit.repository.IUserPlanMappingRepository;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RatelimitServiceImp {
    private final Map<Long, Bucket> bucketCache = new ConcurrentHashMap<>();
    @Autowired
    private IUserPlanMappingRepository userPlanMappingRepository;

    public Bucket resolveBucket(final Long userId) {
        return bucketCache.computeIfAbsent(userId, this::newBucket);
    }

    public void deleteIfExists(final Long userId) {
        bucketCache.remove(userId);
    }

    private Bucket newBucket(Long userId) {
        Plan plan = userPlanMappingRepository.findByUserId(userId).get().getPlan();
        final Integer limitPerHour = plan.getLimitPerHour();
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(limitPerHour, Refill.intervally(limitPerHour, Duration.ofHours(1))))
                .build();
    }
}
