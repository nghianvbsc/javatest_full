package bscenter.tracnghiemlaptrinh.views.fragments;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.SharedPreference;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 03/06/2016
 */

public class SettingFragment extends BaseFragment {
    private CheckBox cbAudio, cbVibrate;

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initViews() {
        cbAudio = (CheckBox) view.findViewById(R.id.cbAudio);
        cbVibrate = (CheckBox) view.findViewById(R.id.cbVibrate);
    }

    @Override
    protected void main() {
        final SharedPreference mSharedPreference = SharedPreference.getInstance(getActivity());
        if (mSharedPreference.isTurnSound()) {
            cbAudio.setChecked(true);
        }
        if (mSharedPreference.isTurnVibrate()) {
            cbVibrate.setChecked(true);
        }

        cbAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharedPreference.onSetting(isChecked, cbVibrate.isChecked());
            }
        });

        cbVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharedPreference.onSetting(cbAudio.isChecked(), isChecked);
            }
        });
    }
}
