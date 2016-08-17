package bscenter.tracnghiemlaptrinh.views.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.interfaces.LevelListener;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 03/06/2016
 */

public class LevelFragment extends BaseFragment implements View.OnClickListener {

    private Button btnEasy, btnMedium, btnDifficult;

    @Override
    protected int getLayout() {
        return R.layout.fragment_level;
    }

    @Override
    protected void initViews() {
        btnEasy = (Button) view.findViewById(R.id.btnEasy);
        btnMedium = (Button) view.findViewById(R.id.btnMedium);
        btnDifficult = (Button) view.findViewById(R.id.btnDifficult);
    }

    @Override
    protected void main() {
        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnDifficult.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (getActivity() instanceof LevelListener) {
            Level level = Level.easy;
            if (view == btnEasy) {
                level = Level.easy;
            } else if (view == btnMedium) {
                level = Level.medium;
            } else if (view == btnDifficult) {
                level = Level.difficult;
            }
            ((LevelListener) getActivity()).onChoiceLevel(level);
        }
    }
}
