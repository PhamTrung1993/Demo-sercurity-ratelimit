package com.example.demosercurityratelimit.repository;

import com.example.demosercurityratelimit.model.Plan;
import com.example.demosercurityratelimit.model.User;
import com.example.demosercurityratelimit.model.UserPlanMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserPlanMappingRepository extends JpaRepository<UserPlanMapping,Integer> {
    Boolean existsByUserAndPlan(User user, Plan plan);

    Optional<UserPlanMapping> findByUserId(Long userId);
}
