package bscenter.tracnghiemlaptrinh.views.fragments;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.SharedPreference;
import bscenter.tracnghiemlaptrinh.model.TimeHelper;
import bscenter.tracnghiemlaptrinh.model.objects.SaveTest;
import bscenter.tracnghiemlaptrinh.views.adapters.BestTestAdapter;
import bscenter.tracnghiemlaptrinh.views.custom.BackgroundView;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 03/06/2016
 */

public class NearScoreFragment extends BaseFragment {
    private View tvEmpty, llDetail;
    private ListView listView;
    private TextView tvScore, tvTime, tvLevel;

    private BestTestAdapter bestTestAdapter;
    private List<SaveTest> bestTest = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_near_score;
    }

    @Override
    protected void initViews() {
        tvEmpty = view.findViewById(R.id.tvEmpty);
        llDetail = view.findViewById(R.id.llDetail);
        tvScore = (TextView) view.findViewById(R.id.tvScore);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvLevel = (TextView) view.findViewById(R.id.tvLevel);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    protected void main() {
        if (SharedPreference.getInstance(getActivity()).isPlayed()) {
            tvEmpty.setVisibility(View.GONE);
            llDetail.setVisibility(View.VISIBLE);
            bestTestAdapter = new BestTestAdapter(getActivity(), bestTest);
            listView.setAdapter(bestTestAdapter);
            getData();
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
            llDetail.setVisibility(View.GONE);
        }
    }

    private void getData() {
        SaveTest laseTest = SaveTest.find(SaveTest.class, "type = ?", new String[]{"1"}).get(0);
        tvScore.setText(laseTest.getScore() + "");
        tvTime.setText("Chơi lúc: " + TimeHelper.convertTimeUtcToLocal(laseTest.getTimePlay()));
        String levelAndTime = "Mức độ: " + BackgroundView.getLevel(Level.findType(laseTest.getLevel()));
        if (laseTest.getLevel() == 2 || laseTest.getLevel() == 3) {
            levelAndTime += " | Thời gian : " + laseTest.getTimeTest() + " giây";
        }
        tvLevel.setText(levelAndTime);


        bestTest.addAll(SaveTest.findWithQuery(SaveTest.class,
                "SELECT * FROM savetest WHERE type = 2 ORDER BY score DESC, time_test ASC"));
        bestTestAdapter.notifyDataSetChanged();

    }
}
