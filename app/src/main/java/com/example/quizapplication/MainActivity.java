package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;

    private TextView scored;

    private TextView percentage;
    private ArrayList<Question> questions;
    private int currentQuestionIndex;

//    private Button play;

//    private Boolean quizStart = false;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionTextView = findViewById(R.id.Question);
        option1 = findViewById(R.id.button4);
        option2 = findViewById(R.id.button5);
        option3 = findViewById(R.id.button6);
        option4 = findViewById(R.id.button7);
//
//        if (quizStart == false) {
//            play = findViewById(R.id.playStart);
//            questionTextView.setVisibility(View.GONE);
//            option1.setVisibility(View.GONE);
//            option2.setVisibility(View.GONE);
//            option3.setVisibility(View.GONE);
//            option4.setVisibility(View.GONE);
//            play.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    quizStart = true;
//                    play.setVisibility(View.GONE);
//                }
//            });
//        } else {
//
//                        option1.setVisibility(View.VISIBLE);
//                        option2.setVisibility(View.VISIBLE);
//                        option3.setVisibility(View.VISIBLE);
//                        option4.setVisibility(View.VISIBLE);

            questions = new ArrayList<>();
            questions.add(new Question("Which of the following is a markup language?", "HTML", "CSS", "JavaScript", "PHP", "a"));
            questions.add(new Question("What year was JavaScript launched?", "1996", "1995", "1994", "none of the above", "b"));
            questions.add(new Question("What does CSS stand for?", "Hypertext Markup Language", "Cascading Style Sheet", "Jason Object Notation", "none of the above", "b"));
            questions.add(new Question("What does HTML stand for?", "Hypertext Markup Language", "Cascading Style Sheet", "Jason Object Notation", "none of the above", "a"));
            questions.add(new Question("What does JS stand for?", "Hypertext Markup Language", "Cascading Style Sheet", "Jason Object Notation", "none of the above", "d"));

            // Set the initial question
            currentQuestionIndex = 0;
            setQuestion();

            // Set onClickListener for option buttons
            if (currentQuestionIndex < questions.size()) {

                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer("a");
                    }
                });

                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer("b");
                    }
                });

                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer("c");
                    }
                });

                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer("d");
                    }
                });
            }
        }
//    }
        private void setQuestion () {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(String.valueOf(currentQuestionIndex + 1) + " " + currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOptionA());
            option2.setText(currentQuestion.getOptionB());
            option3.setText(currentQuestion.getOptionC());
            option4.setText(currentQuestion.getOptionD());
        }

        private void checkAnswer (String selectedOption){
            Question currentQuestion = questions.get(currentQuestionIndex);
            String correctOption = currentQuestion.getCorrectOption();

            if (selectedOption.equals(correctOption)) {
                // Correct answer handling (you can update score or do other actions)
                score++;
            } else {
                // Incorrect answer handling (you can show a message or do other actions)
            }

            // Move to the next question
            currentQuestionIndex++;

            // Check if there are more questions
            if (currentQuestionIndex < questions.size()) {
                setQuestion();
            } else {
                // Quiz finished, handle the end of the quiz (e.g., show a result screen)
                // You may want to reset the index for a replay or other actions
                scored = findViewById(R.id.scored);
                percentage = findViewById(R.id.percentage);

                scored.setVisibility(View.VISIBLE);
                percentage.setVisibility(View.VISIBLE);

                scored.setText("your score is: " + score + "/" + currentQuestionIndex);
                float percent = (score * 100) / currentQuestionIndex;
                percentage.setText("Your Precentagr is " + percent + "%");

                option1.setVisibility(View.GONE);
                option2.setVisibility(View.GONE);
                option3.setVisibility(View.GONE);
                option4.setVisibility(View.GONE);
                Button reset;
                reset = findViewById(R.id.button8);
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scored.setVisibility(View.GONE);
                        percentage.setVisibility(View.GONE);
                        currentQuestionIndex = 0;
                        score = 0;
//                        quizStart = false;
//                        play.setVisibility(View.VISIBLE);

                        option1.setVisibility(View.VISIBLE);
                        option2.setVisibility(View.VISIBLE);
                        option3.setVisibility(View.VISIBLE);
                        option4.setVisibility(View.VISIBLE);
                        setQuestion();
                    }
                });


            }
        }
    }
