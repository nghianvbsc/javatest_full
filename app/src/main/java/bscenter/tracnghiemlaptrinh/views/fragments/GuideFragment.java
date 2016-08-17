package bscenter.tracnghiemlaptrinh.views.fragments;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.views.adapters.GuideAdapter;
import bscenter.tracnghiemlaptrinh.views.custom.CirclePageIndicator;
import bscenter.tracnghiemlaptrinh.views.intalize.BaseFragment;

/**
 * Created by NIT Admin on 03/06/2016
 */

public class GuideFragment extends BaseFragment {
    private ViewPager viewPager;
    private TextView tvTitle;
    private CirclePageIndicator indicater;

    @Override
    protected int getLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initViews() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        indicater = (CirclePageIndicator) view.findViewById(R.id.indicater);
    }

    @Override
    protected void main() {
        viewPager.setAdapter(new GuideAdapter(getFragmentManager()));
        indicater.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == 0) {
                    tvTitle.setText("Khái quát");
                } else if (viewPager.getCurrentItem() == 1) {
                    tvTitle.setText("Cài đặt");
                } else if (viewPager.getCurrentItem() == 2) {
                    tvTitle.setText("Mức độ chơi");
                } else if (viewPager.getCurrentItem() == 3) {
                    tvTitle.setText("Tính điểm");
                } else if (viewPager.getCurrentItem() == 4) {
                    tvTitle.setText("Lưu điểm");
                } else if (viewPager.getCurrentItem() == 5) {
                    tvTitle.setText("Tạm dừng hoặc thoát bài test");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
