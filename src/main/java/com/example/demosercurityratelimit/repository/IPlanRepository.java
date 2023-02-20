package com.example.demosercurityratelimit.repository;

import com.example.demosercurityratelimit.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Long> {
}
