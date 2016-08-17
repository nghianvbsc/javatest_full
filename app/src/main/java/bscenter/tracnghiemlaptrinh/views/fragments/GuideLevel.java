package bscenter.tracnghiemlaptrinh.views.fragments;

import android.widget.TextView;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.views.activities.PlayActivity;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class GuideLevel extends BaseFragment {
    private TextView tvLevelGuide;

    @Override
    protected int getLayout() {
        return R.layout.fragment_level_guide;
    }

    @Override
    protected void initViews() {
        tvLevelGuide = (TextView) view.findViewById(R.id.tvLevelGuide);
    }

    @Override
    protected void main() {
        tvLevelGuide.setText("Ba mức độ trong game là dễ, trung bình, và khó. " +
                "Ở mức độ dễ bạn sẽ phải trả lời 10 câu hỏi không giới hạn thời gian. " +
                "Múc trung bình cung cấp 15 câu hỏi và bạn sẽ có " + PlayActivity.timeMenium + " " +
                "giây để hoàn thành. Cuối cùng là mức khó, thời gian chỉ còn "
                + PlayActivity.timeDif + " giây và số câu hỏi là 20. Sau khi kết thúc bài test, " +
                "hệ thống sẽ tính điểm và đưa ra lời nhận xét.");
    }
}
