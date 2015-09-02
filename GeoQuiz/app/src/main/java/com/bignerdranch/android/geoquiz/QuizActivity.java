package com.bignerdranch.android.geoquiz;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bignerdranch.android.geoquiz.databinding.ActivityQuizBinding;


public class QuizActivity extends AppCompatActivity {

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        QuestionViewModel viewModel = new QuestionViewModel(this, mQuestionBank, mQuestionListener);
        binding.setQuestionViewModel(viewModel);
    }

    private void checkAnswer(Question question, boolean userPressedTrue) {
        boolean answerIsTrue = question.isAnswerTrue();

        int messageResId = R.string.incorrect_toast;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    private QuestionViewModel.QuestionListener mQuestionListener = new QuestionViewModel.QuestionListener() {
        @Override
        public void onAnswer(Question question, boolean userAnswer) {
            checkAnswer(question, userAnswer);
        }
    };

}
