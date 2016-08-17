package bscenter.tracnghiemlaptrinh.views.activities;

import android.content.Intent;
import android.os.Handler;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseActivity;

/**
 * Created by nghianv on 06/08/2016
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void main() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        }, 2000);
    }
}
