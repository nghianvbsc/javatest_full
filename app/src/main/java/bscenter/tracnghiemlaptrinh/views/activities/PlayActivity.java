package bscenter.tracnghiemlaptrinh.views.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.database.DatabaseAccess;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.SharedPreference;
import bscenter.tracnghiemlaptrinh.model.objects.Answer;
import bscenter.tracnghiemlaptrinh.model.objects.Question;
import bscenter.tracnghiemlaptrinh.views.custom.BackgroundView;
import bscenter.tracnghiemlaptrinh.views.custom.ItemAnswer;
import bscenter.tracnghiemlaptrinh.views.custom.QuestionView;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseActivity;

/**
 * Created by NIT Admin on 02/06/2016
 */
@SuppressLint("SetTextI18n")
public class PlayActivity extends BaseActivity
        implements View.OnClickListener,
        ItemAnswer.AnswerListening, BackgroundView.BackgroundListening {

    private View imgBack;
    private TextView tvTime;
    private final List<Question> questions = new ArrayList<>();
    public static long timeMenium = 150;
    public static long timeDif = 140;
    private int currentQuestion = 0;
    private int countTrueAnswer = 0;
    private int timePlay = -1;
    private long allTimeForTest = 0;
    private SharedPreference sharedPreference;
    private Level level;
    private boolean isTurnSound, isVibrate;
    private Vibrator vibrator;
    private MediaPlayer mediaAnswer, mediaStart, mediaEnd;

    private QuestionView questionView;
    private BackgroundView backgroundView;
    private ItemAnswer itemAnswerA, itemAnswerB, itemAnswerC, itemAnswerD;
    private CountDownTimer countDownTimer;

    @Override
    protected int getLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initViews() {
        backgroundView = (BackgroundView) findViewById(R.id.backgroundView);
        questionView = (QuestionView) findViewById(R.id.questionView);
        itemAnswerA = (ItemAnswer) findViewById(R.id.answerA);
        itemAnswerB = (ItemAnswer) findViewById(R.id.answerB);
        itemAnswerC = (ItemAnswer) findViewById(R.id.answerC);
        itemAnswerD = (ItemAnswer) findViewById(R.id.answerD);
        itemAnswerA.setCallBack(this);
        itemAnswerB.setCallBack(this);
        itemAnswerC.setCallBack(this);
        itemAnswerD.setCallBack(this);
        backgroundView.setCallBack(this);
        hideAllViewQuestion();
        imgBack = findViewById(R.id.imgBack);
        tvTime = (TextView) findViewById(R.id.tvTime);
        imgBack.setOnClickListener(this);
        tvTime.setOnClickListener(this);
    }

    private void hideAllViewQuestion() {
        itemAnswerA.setVisibility(View.GONE);
        itemAnswerB.setVisibility(View.GONE);
        itemAnswerC.setVisibility(View.GONE);
        itemAnswerD.setVisibility(View.GONE);
        questionView.setVisibility(View.GONE);
    }

    private void enableClickAnswer(boolean enable) {
        itemAnswerA.setEnabled(enable);
        itemAnswerB.setEnabled(enable);
        itemAnswerC.setEnabled(enable);
        itemAnswerD.setEnabled(enable);
        itemAnswerA.enableClickAnswer(enable);
        itemAnswerB.enableClickAnswer(enable);
        itemAnswerC.enableClickAnswer(enable);
        itemAnswerD.enableClickAnswer(enable);
        questionView.setEnabled(enable);
        tvTime.setEnabled(enable);
        imgBack.setEnabled(enable);
    }

    @Override
    protected void main() {
        sharedPreference = SharedPreference.getInstance(this);
        isTurnSound = sharedPreference.isTurnSound();
        isVibrate = sharedPreference.isTurnVibrate();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaAnswer = MediaPlayer.create(this, R.raw.answer_press);
        mediaStart = MediaPlayer.create(this, R.raw.start);
        mediaEnd = MediaPlayer.create(this, R.raw.finish);

        if (getIntent().getExtras() != null) {
            level = Level.findType(getIntent().getExtras().getInt(HomeActivity.LEVEL));
            backgroundView.onStartGame(level);
            getQuestion();
            if (level == Level.medium) {
                tvTime.setText(timeMenium + "");
            } else if (level == Level.difficult) {
                tvTime.setText(timeDif + "");
            } else {
                tvTime.setText("0");
            }

        } else {
            close();
        }
    }

    private void getQuestion() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<Question> quotes = databaseAccess.getGetQuestion(level);
        questions.addAll(quotes);
        databaseAccess.close();
        Collections.shuffle(questions);
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestion - 1);
        randomAndShowQuestion(question);
        showViewViaAnim();
        addQuery(question.getId());
        enableClickAnswer(true);
    }

    private void randomAndShowQuestion(Question question) {
        String answerAFromdb = question.getAnswerA();
        String answerBFromdb = question.getAnswerB();
        String answerCFromdb = question.getAnswerC();
        String answerDFromdb = question.getAnswerD();
        String trueAnswerFromDb = question.getTrueAnswer();

        Answer answer1 = new Answer(trueAnswerFromDb.equalsIgnoreCase("a"), answerAFromdb);
        Answer answer2 = new Answer(trueAnswerFromDb.equalsIgnoreCase("b"), answerBFromdb);
        Answer answer3 = new Answer(trueAnswerFromDb.equalsIgnoreCase("c"), answerCFromdb);
        Answer answer4 = new Answer(trueAnswerFromDb.equalsIgnoreCase("d"), answerDFromdb);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        Collections.shuffle(answers);

        questionView.setData(currentQuestion, questions.size(), question.getQuestion());
        itemAnswerA.setData("A", answers.get(0).getContent(), answers.get(0).isTrueAnswer());
        itemAnswerB.setData("B", answers.get(1).getContent(), answers.get(1).isTrueAnswer());
        itemAnswerC.setData("C", answers.get(2).getContent(), answers.get(2).isTrueAnswer());
        itemAnswerD.setData("D", answers.get(3).getContent(), answers.get(3).isTrueAnswer());

    }

    private void addQuery(int questionId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        databaseAccess.addQuery(questionId);
        databaseAccess.close();
    }

    @Override
    public void onClick(View v) {
        if (v == imgBack) {
            if (currentQuestion == 0) {
                close();
            } else {
                if (isShowQuestion()) {
                    if (countDownTimer != null) countDownTimer.cancel();
                    backgroundView.onPauseAnswer(countTrueAnswer, questions.size(), level);
                    hideAllViewQuestion();
                }
            }
        } else if (v == tvTime) {
            if (isShowQuestion()) {
                if (countDownTimer != null) countDownTimer.cancel();
                backgroundView.onPauseAnswer(countTrueAnswer, questions.size(), level);
                hideAllViewQuestion();
            }
        }
    }

    private boolean isShowQuestion() {
        return questionView.isShown();
    }

    private void showViewViaAnim() {
        TranslateAnimation animInA = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
        animInA.setStartOffset(10);
        TranslateAnimation animInB = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_in);
        animInB.setStartOffset(30);
        TranslateAnimation animInC = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
        animInC.setStartOffset(60);
        TranslateAnimation animInD = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_in);
        animInD.setStartOffset(90);
        TranslateAnimation animInQuestion = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        animInQuestion.setStartOffset(10);

        itemAnswerA.setVisibility(View.VISIBLE);
        itemAnswerB.setVisibility(View.VISIBLE);
        itemAnswerC.setVisibility(View.VISIBLE);
        itemAnswerD.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        itemAnswerA.startAnimation(animInA);
        itemAnswerC.startAnimation(animInC);
        itemAnswerB.startAnimation(animInB);
        itemAnswerD.startAnimation(animInD);
        questionView.startAnimation(animInQuestion);
    }

    private void hideViewViaAnim() {
        TranslateAnimation animOutLeft = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_out);
        TranslateAnimation animOutRight = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_out);
        TranslateAnimation animOutTop = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_top_out);
        animOutRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideAllViewQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        itemAnswerA.startAnimation(animOutLeft);
        itemAnswerC.startAnimation(animOutLeft);
        itemAnswerB.startAnimation(animOutRight);
        itemAnswerD.startAnimation(animOutRight);
        questionView.startAnimation(animOutTop);
    }

    @Override
    public void onAnswer(final boolean isAnswer) {
        onEffectAnswerClick();
        if (isAnswer) {
            countTrueAnswer++;
        }
        enableClickAnswer(false);
        hideViewViaAnim();
        backgroundView.onAnswerChange(countTrueAnswer, questions.size(), level);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentQuestion <= questions.size() - 1) {
                    currentQuestion++;
                    showQuestion();
                } else {
                    if (isTurnSound) mediaEnd.start();
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        countDownTimer = null;
                    }
                    saveTest();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            backgroundView.onFinishAnswer(countTrueAnswer, questions.size(), level);
                        }
                    }, 300);
                }
            }
        }, 700);
    }

    @Override
    public void onStartGame() {
        if (isTurnSound) mediaStart.start();
        currentQuestion++;
        allTimeForTest = level == Level.medium ? timeMenium : level == Level.difficult ? timeDif : 0;
        if (allTimeForTest != 0) {
            initTimer(allTimeForTest);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showQuestion();
            }
        }, 360);
    }

    @Override
    public void onContinueGame() {
        if (level == Level.medium || level == Level.difficult) {
            initTimer(Integer.parseInt(tvTime.getText().toString()));
        }
        showQuestion();
    }

    @Override
    public void onExitGame() {
        close();
    }

    private void finishTime() {
        enableClickAnswer(false);
        hideViewViaAnim();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                saveTest();
                backgroundView.onFinishAnswer(countTrueAnswer, questions.size(), level);
            }
        }, 300);
    }

    private void saveTest() {
        if (allTimeForTest != 0) {
            timePlay = Integer.parseInt(String.valueOf(allTimeForTest)) - Integer.parseInt(tvTime.getText().toString());
        }
        sharedPreference.saveLastTest(level,
                backgroundView.getScore(countTrueAnswer, questions.size(), level),
                countTrueAnswer, questions.size(),
                timePlay);
    }

    private void initTimer(long time) {
        long newTime = time * 1000;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

        countDownTimer = new CountDownTimer(newTime, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                tvTime.setText("0");
                finishTime();
            }
        };
        countDownTimer.start();
    }

    private void onEffectAnswerClick() {
        if (isTurnSound) {
            mediaAnswer.start();
        }
        if (isVibrate) {
            vibrator.vibrate(200);
        }
    }

    @Override
    protected void close() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        super.close();
    }

    @Override
    public void onBackPressed() {
        imgBack.performClick();
    }
}
