package com.example.hackathon.repository;

import com.example.hackathon.entity.Toxic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToxicRepository extends JpaRepository<Toxic, Long> {
}
