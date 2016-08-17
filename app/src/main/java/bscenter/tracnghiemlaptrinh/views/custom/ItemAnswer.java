package bscenter.tracnghiemlaptrinh.views.custom;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bscenter.tracnghiemlaptrinh.R;

/**
 * Created by NIT Admin on 04/06/2016
 */

public class ItemAnswer extends RelativeLayout {

    private TextView tvTitle, tvContent;
    private View rlRoot;
    private String mAnswer, mContent;
    private boolean mTrueAnswer;
    private AnswerListening mAnswerListening;

    public ItemAnswer(Context context) {
        this(context, null);
    }

    public ItemAnswer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemAnswer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.item_answer, null);
        initViews(view);
        addView(view);
    }

    private void initViews(View view) {
        rlRoot = view.findViewById(R.id.rlRoot);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);

        rlRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTrueAnswer) {
                    mAnswerListening.onAnswer(true);
                } else {
                    mAnswerListening.onAnswer(false);
                }
            }
        });
    }

    public void setCallBack(AnswerListening answerListening) {
        mAnswerListening = answerListening;
    }

    public void setData(String answer, String content, boolean trueAnswer) {
        mAnswer = answer;
        mContent = content;
        mTrueAnswer = trueAnswer;
        fakeData();
    }

    public void enableClickAnswer(boolean enable) {
        rlRoot.setEnabled(enable);
    }

    private void fakeData() {
        tvTitle.setText(mAnswer);
        tvContent.setText(Html.fromHtml(mContent));

    }

    public interface AnswerListening {
        void onAnswer(boolean isAnswer);
    }
}
