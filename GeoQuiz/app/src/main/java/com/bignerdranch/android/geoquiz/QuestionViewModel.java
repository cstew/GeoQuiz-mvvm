package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

public class QuestionViewModel extends BaseObservable {

    private Context mContext;
    private Question[] mQuestions;
    private QuestionListener mQuestionListener;

    private int mCurrentIndex;

    public interface QuestionListener {
        void onAnswer(Question question, boolean userAnswer);
    }

    public QuestionViewModel(Context context, Question[] questions, QuestionListener questionListener) {
        mContext = context;
        mQuestions = questions;
        mQuestionListener = questionListener;
    }

    @Bindable
    public String getQuestionText() {
        return mContext.getString(mQuestions[mCurrentIndex].getTextResId());
    }

    public void onAnswer(boolean answerIsTrue) {
        Question question = mQuestions[mCurrentIndex];
        mQuestionListener.onAnswer(question, answerIsTrue);
    }

    public void onNextClick() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
        notifyPropertyChanged(BR.questionText);
    }

    public View.OnClickListener getOnNextClickListener() {
        return mOnNextClickListener;
    }

    public View.OnClickListener getOnTrueClickListener() {
        return mOnTrueClickListener;
    }

    public View.OnClickListener getOnFalseClickListener() {
        return mOnFalseClickListener;
    }

    private View.OnClickListener mOnNextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onNextClick();
        }
    };

    private View.OnClickListener mOnTrueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onAnswer(true);
        }
    };

    private View.OnClickListener mOnFalseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onAnswer(false);
        }
    };

}
