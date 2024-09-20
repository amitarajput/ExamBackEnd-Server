package com.exam.controller;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //get question
    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable("questionId") Long questionId) {
        return this.questionService.getQuestion(questionId);
    }
    //get all questions of any quiz

    @GetMapping("/quiz/{qid}")

    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
        //load quiz first we will get no of questions

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.deleteQuestion(questionId);
    }
    //eval quiz
//    @PostMapping("/eval-quiz")
//    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
//        System.out.println(questions);
//        double marksGot=0;
//        int correctAnswers=0;
//        int attempted=0;
//        for(Question q: questions){
//            //get single question
//          Question question = this.questionService.getQuestions(q.getQid());
//          if(question.getAnswer().equals(q.getGivenAnswer().trim()))
//          {
//              //question correct
//              correctAnswers++;
//              //calculate single marks
//              double marksSingle= Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
//              marksGot += marksSingle;
//          }
//              //not correct
//              if (q.getGivenAnswer()!=null) {
//                 attempted++;
//
//              }
//          }
//        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
//
//        return ResponseEntity.ok(map);
//    }

}
