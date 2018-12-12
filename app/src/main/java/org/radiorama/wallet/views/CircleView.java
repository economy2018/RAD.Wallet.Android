package org.radiorama.wallet.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.radiorama.wallet.R;
import org.radiorama.wallet.utils.ScreenUtils;

import java.util.ArrayList;

public class CircleView extends FrameLayout {
    private static final String TAG = CircleView.class.getSimpleName();

    private Context mContext;
    private int res[] = {R.id.circle_menu_button_1,R.id.circle_menu_button_2,R.id.circle_menu_button_3,R.id.circle_menu_button_4,R.id.circle_menu_button_5};
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private boolean mFlag = true;
    private static final int DISTANCE = 120;

    public CircleView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.circle_menu_layout, this, true);
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = rootView.findViewById(res[i]);
            initListener(imageView, i);
            imageViews.add(imageView);
        }
    }

    private void initListener(ImageView imageView, final int position){
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrClose();
                if(position==0 && mBtn1Listener!=null) mBtn1Listener.OnClick();
                if(position==1 && mBtn2Listener!=null) mBtn2Listener.OnClick();
                if(position==2 && mBtn3Listener!=null) mBtn3Listener.OnClick();
                if(position==3 && mBtn4Listener!=null) mBtn4Listener.OnClick();
                if(position==4 && mBtn5Listener!=null) mBtn5Listener.OnClick();
            }
        });
    }

    private BtnClickListener mBtn1Listener;
    public void setBtn1Listener(BtnClickListener listener){
        this.mBtn1Listener = listener;
    }

    private BtnClickListener mBtn2Listener;
    public void setBtn2Listener(BtnClickListener listener){
        this.mBtn2Listener = listener;
    }

    private BtnClickListener mBtn3Listener;
    public void setBtn3Listener(BtnClickListener listener){
        this.mBtn3Listener = listener;
    }

    private BtnClickListener mBtn4Listener;
    public void setBtn4Listener(BtnClickListener listener){
        this.mBtn4Listener = listener;
    }

    private BtnClickListener mBtn5Listener;
    public void setBtn5Listener(BtnClickListener listener){
        this.mBtn5Listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void openOrClose(){
        Log.i(TAG, "flag:"+mFlag);
        rotationAnimator(mFlag? DISTANCE: 0);
    }

    private void rotationAnimator(int dp) {
        mFlag = !mFlag;
        for (int i = 1; i < res.length; i++) {
            AnimatorSet set = new AnimatorSet();
            double x = -Math.cos(0.5/(res.length-2)*(i-1)*Math.PI)* ScreenUtils.dip2px(mContext, dp);
            double y = -Math.sin(0.5/(res.length-2)*(i-1)*Math.PI)* ScreenUtils.dip2px(mContext, dp);
            set.playTogether(
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationX",(float)(x*0.25),(float)x),
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationY",(float)(y*0.25),(float)y)
                    ,ObjectAnimator.ofFloat(imageViews.get(i),"alpha",0,1).setDuration(2000)
            );
            set.setInterpolator(new BounceInterpolator());
            set.setDuration(500).setStartDelay(100*i);
            set.start();
        }
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageViews.get(0),"rotation",0,45).setDuration(300);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();
    }

    interface BtnClickListener {
        void OnClick();
    }
}
