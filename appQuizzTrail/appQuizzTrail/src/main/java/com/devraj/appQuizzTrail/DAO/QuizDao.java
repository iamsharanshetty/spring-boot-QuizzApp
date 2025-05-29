package com.devraj.appQuizzTrail.DAO;

import com.devraj.appQuizzTrail.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
