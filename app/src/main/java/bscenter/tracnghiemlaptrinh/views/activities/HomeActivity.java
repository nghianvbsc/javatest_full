package bscenter.tracnghiemlaptrinh.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.interfaces.LevelListener;
import bscenter.tracnghiemlaptrinh.views.fragments.GuideFragment;
import bscenter.tracnghiemlaptrinh.views.fragments.IntroduceFragment;
import bscenter.tracnghiemlaptrinh.views.fragments.LevelFragment;
import bscenter.tracnghiemlaptrinh.views.fragments.NearScoreFragment;
import bscenter.tracnghiemlaptrinh.views.fragments.SettingFragment;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseActivity;

public class HomeActivity extends BaseActivity
        implements View.OnClickListener, LevelListener {

    static final String LEVEL = "level";
    private static final int CODE_PLAY = 100;
    private Button btnPlay, btnNearScore, btnSetting, btnGuide;
    private FrameLayout frMainHome;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnNearScore = (Button) findViewById(R.id.btnNearScore);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnGuide = (Button) findViewById(R.id.btnGuide);
        frMainHome = (FrameLayout) findViewById(R.id.frMainHome);
        btnPlay.setOnClickListener(this);
        btnNearScore.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnGuide.setOnClickListener(this);

        isVisibleButton(false);
        frMainHome.setVisibility(View.GONE);
    }

    @Override
    protected void main() {
        replayFragment(new IntroduceFragment(), R.id.frMainHome);
        animationShowMenu();
        animationShowFragmentHome();
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v == btnPlay) {
            fragment = new LevelFragment();
        } else if (v == btnNearScore) {
            fragment = new NearScoreFragment();
        } else if (v == btnSetting) {
            fragment = new SettingFragment();
        } else if (v == btnGuide) {
            fragment = new GuideFragment();
        }
        replayFragment(fragment, R.id.frMainHome);
    }

    private void animationShowMenu() {
        int timeDelayBetween = 200;
        int timeStart = 10;
        final List<View> listButton = new ArrayList<>();
        listButton.add(btnPlay);
        listButton.add(btnNearScore);
        listButton.add(btnSetting);
        listButton.add(btnGuide);

        for (int index = 0; index < listButton.size(); index++) {
            TranslateAnimation translateAnimation =
                    (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
            translateAnimation.setStartOffset(timeStart);
            listButton.get(index).setVisibility(View.VISIBLE);
            listButton.get(index).startAnimation(translateAnimation);
            timeStart += timeDelayBetween;
        }
    }

    private void animationShowFragmentHome() {
        int timeDelay = 220;
        TranslateAnimation translateAnimation =
                (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_right_in);
        translateAnimation.setStartOffset(timeDelay);
        frMainHome.setVisibility(View.VISIBLE);
        frMainHome.startAnimation(translateAnimation);
    }

    @Override
    public void onChoiceLevel(Level level) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(LEVEL, level.getValue());
        openActivity(intent, CODE_PLAY, OPEN);
    }

    private void isVisibleButton(boolean isVisible) {
        btnPlay.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        btnNearScore.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        btnSetting.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        btnGuide.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        isVisibleButton(false);
        frMainHome.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationShowMenu();
                animationShowFragmentHome();
            }
        }, 300);
    }
}