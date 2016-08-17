package bscenter.tracnghiemlaptrinh.views.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import bscenter.tracnghiemlaptrinh.R;

/**
 * Created by NIT Admin on 04/06/2016
 */
@SuppressLint("InflateParams")
public class QuestionView extends ScrollView {

    private TextView tvTitle, tvContent;
    private ScrollView scrollView;

    public QuestionView(Context context) {
        this(context, null);
    }

    public QuestionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.question_view, null);
        initViews(view);
        addView(view);
    }

    private void initViews(View view) {
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
    }

    public void setData(int numQuestion, int countQuestion, String question) {
        tvTitle.setText("Câu: " + numQuestion + "/" + countQuestion);
        tvContent.setText(Html.fromHtml(question));
        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }
}
