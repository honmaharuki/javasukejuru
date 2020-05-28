package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Priorities;


public interface PrioritiesRepository extends JpaRepository<Priorities, Integer> {
}
