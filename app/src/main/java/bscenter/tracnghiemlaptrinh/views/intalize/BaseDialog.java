package bscenter.tracnghiemlaptrinh.views.intalize;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by NIT Admin on 03/06/2016
 */

public abstract class BaseDialog extends Dialog {

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void main();

    public BaseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        main();
    }
}
