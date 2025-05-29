package com.devraj.appQuizzTrail.service;

import com.devraj.appQuizzTrail.DAO.QuestionDao;
import com.devraj.appQuizzTrail.DAO.QuizDao;
import com.devraj.appQuizzTrail.model.Question;
import com.devraj.appQuizzTrail.model.QuestionWrapper;
import com.devraj.appQuizzTrail.model.Quiz;
import com.devraj.appQuizzTrail.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class QuizService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category,int numq,String title) {

        List<Question> question = questionDao.getRandomQuizByCategory(category,numq);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz Created!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUSer = new ArrayList<>();
        for(Question q: questionFromDB)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUSer.add(qw);
        }
        return new ResponseEntity<>(questionForUSer,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateQuiz(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
