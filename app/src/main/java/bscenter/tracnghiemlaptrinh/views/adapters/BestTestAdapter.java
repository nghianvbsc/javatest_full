package bscenter.tracnghiemlaptrinh.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bscenter.tracnghiemlaptrinh.R;
import bscenter.tracnghiemlaptrinh.model.Level;
import bscenter.tracnghiemlaptrinh.model.TimeHelper;
import bscenter.tracnghiemlaptrinh.model.objects.SaveTest;
import bscenter.tracnghiemlaptrinh.views.custom.BackgroundView;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class BestTestAdapter extends BaseAdapter {
    private List<SaveTest> bestTest;
    private static final int ROW_DONT_TIME = 0;
    private static final int ROW_HAS_TIME = 1;
    private Context context;

    public BestTestAdapter(Context context, List<SaveTest> bestTest) {
        this.context = context;
        this.bestTest = bestTest;
    }

    @Override
    public int getCount() {
        return bestTest == null ? 0 : bestTest.size();
    }

    @Override
    public SaveTest getItem(int position) {
        return bestTest.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getLevel() == 1) {
            return ROW_DONT_TIME;
        } else {
            return ROW_HAS_TIME;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_best_test, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvScore = (TextView) view.findViewById(R.id.tvScore);
            viewHolder.tvTrueAnswer = (TextView) view.findViewById(R.id.tvTrueAnswer);
            viewHolder.tvTimePlay = (TextView) view.findViewById(R.id.tvTimePlay);
            viewHolder.tvTimeDevice = (TextView) view.findViewById(R.id.tvTimeDevice);
            viewHolder.tvLevel = (TextView) view.findViewById(R.id.tvLevel);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (getItemViewType(position) == ROW_DONT_TIME) {
            viewHolder.tvTimePlay.setVisibility(View.GONE);
        } else {
            viewHolder.tvTimePlay.setVisibility(View.VISIBLE);
            viewHolder.tvTimePlay.setText("Thời gian: " + getItem(position).getTimeTest()+"s");
        }
        viewHolder.tvScore.setText("Điểm: " + getItem(position).getScore());
        viewHolder.tvTrueAnswer.setText("Đúng: " + getItem(position).getTrueAnswer() + "/" + getItem(position).getCountQuestions());
        viewHolder.tvTimeDevice.setText("Chơi lúc: " + TimeHelper.convertTimeUtcToLocal(getItem(position).getTimePlay()));
        viewHolder.tvLevel.setText("Mức độ: " + BackgroundView.getLevel(Level.findType(getItem(position).getLevel())));

        return view;
    }

    class ViewHolder {
        private TextView tvScore, tvTrueAnswer, tvTimePlay, tvTimeDevice, tvLevel;
    }
}
