package com.psychology.web.questions.repo;

import com.psychology.web.questions.entity.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
}
