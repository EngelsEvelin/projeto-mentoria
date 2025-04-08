package com.example.servicedesk.application.service;

import com.example.servicedesk.domain.model.Problem;
import com.example.servicedesk.domain.model.ProblemStatus;
import com.example.servicedesk.infra.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public Problem resolveProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problema não encontrado"));
        problem.setStatus(ProblemStatus.RESOLVED);
        return problemRepository.save(problem);
    }
}
