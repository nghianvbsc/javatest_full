package bscenter.tracnghiemlaptrinh.views.custom;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.views.activities.PlayActivity;

/**
 * Created by NIT Admin on 04/06/2016
 */

public class BackgroundView extends RelativeLayout implements View.OnClickListener {

    public static int scoreForATrueAnswer = 10;

    //view start game
    private View rlStartGame1, btnStartPlay1, rlStartGame2, btnStartPlay2;
    private TextView tvTitle1, tvTitle2;

    //view score
    private View rlScore;
    private TextView tvScore, tvTrueAnswer;

    //view pause game
    private View rlPause1, btnContinuePause1, btnExitPause1, rlPause2, btnContinuePause2, btnExitPause2;
    private TextView tvScorePause1, tvTrueAnswerPause1, tvLevelPause1, tvScorePause2, tvTrueAnswerPause2, tvLevelPause2;

    ///view finish
    private View rlFinish, btnExitFinish;
    private TextView tvScoreFinish, tvTrueAnswerFinish, tvLevelFinish, tvReviewFinish;

    private BackgroundListening mBackgroundListening;

    public BackgroundView(Context context) {
        this(context, null);
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_background, null);
        initViews(view);
        addView(view);
    }

    private void initViews(View view) {
        //init view start
        rlStartGame1 = view.findViewById(R.id.rlStartGame1);
        rlStartGame2 = view.findViewById(R.id.rlStartGame2);
        btnStartPlay1 = view.findViewById(R.id.btnStartPlay1);
        btnStartPlay2 = view.findViewById(R.id.btnStartPlay2);
        tvTitle1 = (TextView) view.findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) view.findViewById(R.id.tvTitle2);
        btnStartPlay1.setOnClickListener(this);
        btnStartPlay2.setOnClickListener(this);

        //init view pause
        rlScore = view.findViewById(R.id.rlScore);
        tvScore = (TextView) view.findViewById(R.id.tvScore);
        tvTrueAnswer = (TextView) view.findViewById(R.id.tvTrueAnswer);

        //init view pause
        rlPause1 = view.findViewById(R.id.rlPause1);
        rlPause2 = view.findViewById(R.id.rlPause2);
        btnContinuePause1 = view.findViewById(R.id.btnContinuePause1);
        btnExitPause1 = view.findViewById(R.id.btnExitPause1);
        btnContinuePause2 = view.findViewById(R.id.btnContinuePause2);
        btnExitPause2 = view.findViewById(R.id.btnExitPause2);
        tvScorePause1 = (TextView) view.findViewById(R.id.tvScorePause1);
        tvTrueAnswerPause1 = (TextView) view.findViewById(R.id.tvTrueAnswerPause1);
        tvLevelPause1 = (TextView) view.findViewById(R.id.tvLevelPause1);
        tvScorePause2 = (TextView) view.findViewById(R.id.tvScorePause2);
        tvTrueAnswerPause2 = (TextView) view.findViewById(R.id.tvTrueAnswerPause2);
        tvLevelPause2 = (TextView) view.findViewById(R.id.tvLevelPause2);
        btnContinuePause1.setOnClickListener(this);
        btnExitPause1.setOnClickListener(this);
        btnContinuePause2.setOnClickListener(this);
        btnExitPause2.setOnClickListener(this);

        //init view finish
        rlFinish = view.findViewById(R.id.rlFinish);
        btnExitFinish = view.findViewById(R.id.btnExitFinish);
        tvScoreFinish = (TextView) view.findViewById(R.id.tvScoreFinish);
        tvTrueAnswerFinish = (TextView) view.findViewById(R.id.tvTrueAnswerFinish);
        tvLevelFinish = (TextView) view.findViewById(R.id.tvLevelFinish);
        tvReviewFinish = (TextView) view.findViewById(R.id.tvReviewFinish);
        btnExitFinish.setOnClickListener(this);
    }

    public void setCallBack(BackgroundListening backgroundListening) {
        mBackgroundListening = backgroundListening;
    }

    public void onStartGame(Level level) {
        if (randInt(1, 2) == 1) {
            rlStartGame1.setVisibility(View.VISIBLE);
            if (level == Level.medium) {
                tvTitle1.setText("Bạn có " + PlayActivity.timeMenium + " giây.");
            } else if (level == Level.difficult) {
                tvTitle1.setText("Bạn có " + PlayActivity.timeDif + " giây.");
            }
            rlStartGame2.setVisibility(View.GONE);
        } else {
            rlStartGame2.setVisibility(View.VISIBLE);
            if (level == Level.medium) {
                tvTitle2.setText("Thời gian là " + PlayActivity.timeMenium + " giây.");
            } else if (level == Level.difficult) {
                tvTitle2.setText("Thời gian là " + PlayActivity.timeDif + " giây.");
            }
            rlStartGame1.setVisibility(View.GONE);
        }

        rlScore.setVisibility(View.GONE);
        rlPause1.setVisibility(View.GONE);
        rlPause2.setVisibility(View.GONE);
        rlFinish.setVisibility(View.GONE);
    }

    public void onAnswerChange(int countTrueAnswer, int size, Level level) {
        rlScore.setVisibility(View.VISIBLE);
        rlPause1.setVisibility(View.GONE);
        rlPause2.setVisibility(View.GONE);
        rlFinish.setVisibility(View.GONE);
        rlStartGame1.setVisibility(View.GONE);
        rlStartGame2.setVisibility(View.GONE);

        tvScore.setText("Điểm: " + getScore(countTrueAnswer, size, level));
        tvTrueAnswer.setText("Đúng : " + countTrueAnswer + "/" + size + " câu");
    }

    public void onPauseAnswer(int countTrueAnswer, int size, Level level) {
        if (randInt(1, 2) == 1) {
            rlPause1.setVisibility(View.VISIBLE);
            rlPause2.setVisibility(View.GONE);
        } else {
            rlPause2.setVisibility(View.VISIBLE);
            rlPause1.setVisibility(View.GONE);
        }
        rlScore.setVisibility(View.GONE);
        rlFinish.setVisibility(View.GONE);
        rlStartGame1.setVisibility(View.GONE);
        rlStartGame2.setVisibility(View.GONE);

        tvScorePause1.setText("Điểm hiện tại: " + getScore(countTrueAnswer, size, level));
        tvScorePause2.setText("Điểm hiện tại: " + getScore(countTrueAnswer, size, level));
        tvTrueAnswerPause1.setText("Trả lời đúng: " + countTrueAnswer + "/" + size);
        tvTrueAnswerPause2.setText("Trả lời đúng: " + countTrueAnswer + "/" + size);
        tvLevelPause1.setText("Mức độ: " + getLevel(level));
        tvLevelPause2.setText("Mức độ: " + getLevel(level));
    }

    public void onFinishAnswer(int countTrueAnswer, int size, Level level) {
        rlPause2.setVisibility(View.GONE);
        rlPause1.setVisibility(View.GONE);
        rlScore.setVisibility(View.GONE);
        rlFinish.setVisibility(View.VISIBLE);
        rlStartGame1.setVisibility(View.GONE);
        rlStartGame2.setVisibility(View.GONE);

        tvScoreFinish.setText("Điểm : " + getScore(countTrueAnswer, size, level));
        tvTrueAnswerFinish.setText("Trả lời đúng: " + countTrueAnswer + "/" + size);
        tvLevelFinish.setText("Mức độ: " + getLevel(level));
        tvReviewFinish.setText(getReview(countTrueAnswer, size, level));
        btnExitFinish.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnExitFinish.setVisibility(View.VISIBLE);
            }
        }, 2500);
    }

    private String getReview(int countTrueAnswer, int size, Level level) {
        if (level.getValue() == 1) {
            if (getScore(countTrueAnswer, size, level) < 60) {
                return "Thầy sẽ kiểm tra lại kiến thứ của em lần sau!";
            } else if (getScore(countTrueAnswer, size, level) < 80) {
                return "Khá, nhưng cần có gắng th!";
            } else {
                return "Rất tôt! thầy sẽ gửi cho em một bài test khó hơn.";
            }
        } else if (level.getValue() == 2) {
            if (getScore(countTrueAnswer, size, level) < 80) {
                return "Em đã có gắng để chinh phuc mức độ này, nhưng nỗ lực của em vẫn chưa ";
            } else if (getScore(countTrueAnswer, size, level) < 120) {
                return "Em hy chinh mức độ khó hơn xem sao.";
            } else {
                return "Thầy tin em sẽ có kết quả tốt nếu tiếp tục như vậy";
            }
        } else {
            if (getScore(countTrueAnswer, size, level) < 120) {
                return "Hãy suy nghĩ kỹ hơn trước khi chọn đáp án nhé!";
            } else if (getScore(countTrueAnswer, size, level) < 180) {
                return "Thầy và em sẽ gặp nhau ở bài test tiếp.";
            } else {
                return "Thật không thể tin nỗi, em đã qua môn thầy. Chúc em thanh công";
            }
        }
    }

    public int getScore(int countTrueAnswer, int size, Level level) {
        return countTrueAnswer * scoreForATrueAnswer;
    }

    public static String getLevel(Level level) {
        if (level == Level.easy) {
            return "Dễ";
        } else if (level == Level.medium) {
            return "Trung bình";
        } else {
            return "Khó";
        }
    }

    private int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public void onClick(View v) {
        if (v == btnStartPlay1 || v == btnStartPlay2) {
            mBackgroundListening.onStartGame();
        } else if (v == btnContinuePause1 || v == btnContinuePause2) {
            mBackgroundListening.onContinueGame();
        } else if (v == btnExitPause1 || v == btnExitPause2) {
            mBackgroundListening.onExitGame();
        } else if (v == btnExitFinish) {
            mBackgroundListening.onExitGame();
        }
    }


    public interface BackgroundListening {
        void onStartGame();

        void onContinueGame();

        void onExitGame();
    }
}
