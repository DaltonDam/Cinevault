package com.cinevault.repository;

import com.cinevault.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
