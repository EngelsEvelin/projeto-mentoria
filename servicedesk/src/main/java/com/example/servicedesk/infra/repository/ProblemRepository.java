package com.example.servicedesk.infra.repository;

import com.example.servicedesk.domain.model.Problem;
import com.example.servicedesk.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByAssignedDeveloper(User developer);
}
