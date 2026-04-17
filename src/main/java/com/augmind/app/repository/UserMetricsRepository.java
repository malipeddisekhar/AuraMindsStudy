package com.augmind.app.repository;

import com.augmind.app.domain.UserMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetricsRepository extends JpaRepository<UserMetrics, Long> {
}
