package bscenter.tracnghiemlaptrinh.views.intalize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import bscenter.tracnghiemlaptrinh.R;

/**
 * Created by NIT Admin on 02/06/2016
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static final int OPEN = 1;
    protected static final int CLOSE = 2;
    protected static final int NONE = 0;

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void main();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        main();
    }

    /**
     * @param intent
     * @param type:  OPEN or CLOSE
     */
    protected void openActivity(Intent intent, int code, int type) {
        startActivityForResult(intent, code);
        if (type == OPEN) {
            overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        } else {
            overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        }
    }

    /**
     * close activity
     */
    protected void close() {
        finish();
        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
    }

    /**
     * @param fragment
     * @param viewId
     */
    protected void replayFragment(Fragment fragment, int viewId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewId, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
