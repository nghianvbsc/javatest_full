package bscenter.tracnghiemlaptrinh.views.fragments;

import android.widget.TextView;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.views.custom.BackgroundView;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class GuideScoreAndTime extends BaseFragment {

    private TextView tvDetail;

    @Override
    protected int getLayout() {
        return R.layout.fragment_score_and_time_guide;
    }

    @Override
    protected void initViews() {
        tvDetail = (TextView) view.findViewById(R.id.tvDetail);
    }

    @Override
    protected void main() {
        tvDetail.setText("Mỗi câu trả lời đúng bạn sẽ nhận đựơc " + BackgroundView.scoreForATrueAnswer
                + " điểm. Kết thú bài test hệ thống sẽ thông báo cho bạn biết số điểm bạn đạt được và " +
                "một số thông tin khác về bài test của bạn.");
    }
}
