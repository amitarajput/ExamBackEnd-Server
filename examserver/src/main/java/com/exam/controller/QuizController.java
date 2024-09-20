package com.exam.controller;

import com.exam.entities.exam.Category;
import com.exam.entities.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    //add Quiz
    public ResponseEntity<Quiz> addCategory(@RequestBody Quiz quiz){
        Quiz quiz1 = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    //get quiz
    @GetMapping("/{quizId}")
    public Quiz getCategory(@PathVariable("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);
    }
    //get all quizzes

    @GetMapping("/")

    public ResponseEntity<?> getQuizzes()
    {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    //update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }
    //delete quiz
    @DeleteMapping("/{quizId}")
    public void deleteCategory(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }
   //get quiz by categories
    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid)
    {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }
    //get active quiz
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes()
    {
       return this.quizService.getActiveQuizzes();
    }
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesAndCategory(@PathVariable("cid") Long cid)
    {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }
}




