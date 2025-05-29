package com.devraj.appQuizzTrail.DAO;

import com.devraj.appQuizzTrail.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

//    List<Question> findByCat(String category);

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM  question q WHERE q.category= :category ORDER BY RANDOM() LIMIT :numq",nativeQuery = true)
    List<Question> getRandomQuizByCategory(String category, int numq);
}
