package com.kongzue.holderview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.holderview.callback.OnRetryButtonClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kongzue
 * @github: https://github.com/kongzue/
 * @homepage: http://kongzue.com/
 * @mail: myzcxhh@live.cn
 * @createTime: 2019/8/2 0:48
 */
public class HolderView extends RelativeLayout {
    
    private Context context;
    
    private LinearLayout tipView;
    
    private List<View> childViews;
    
    private int emptyImageResId = R.mipmap.img_empty_data;
    private int badNetResId = R.mipmap.img_error_net;
    private int errorImageResId = R.mipmap.img_error_data;
    
    private String emptyText = "暂无任何数据";
    private String badNetText = "网络不稳定，请稍候重试";
    private String errorText = "加载错误，请稍候重试";
    
    private int themeColor = -1;
    
    private String retryButtonText = "点击重试";
    private boolean showRetryButton = true;
    
    private OnRetryButtonClickListener onRetryButtonClickListener;
    
    public HolderView(Context context) {
        super(context);
        init(context, null);
    }
    
    public HolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    
    public HolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        
        tipView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_holder_view, null, false);
        tipView.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tipView.setVisibility(GONE);
        addView(tipView);
        
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HolderView);
            emptyText = getResString(typedArray, R.styleable.HolderView_emptyText, emptyText);
            badNetText = getResString(typedArray, R.styleable.HolderView_badNetText, badNetText);
            errorText = getResString(typedArray, R.styleable.HolderView_errorText, errorText);
            
            emptyImageResId = typedArray.getResourceId(R.styleable.HolderView_emptyImageResId, emptyImageResId);
            badNetResId = typedArray.getResourceId(R.styleable.HolderView_badNetResId, badNetResId);
            errorImageResId = typedArray.getResourceId(R.styleable.HolderView_errorImageResId, errorImageResId);
            
            themeColor = typedArray.getColor(R.styleable.HolderView_themeColor,themeColor);
            
            retryButtonText = getResString(typedArray, R.styleable.HolderView_retryButtonText, retryButtonText);
            showRetryButton = typedArray.getBoolean(R.styleable.HolderView_retryButtonVisibility, showRetryButton);
            typedArray.recycle();
        }
        
        refreshViews();
    }
    
    public void showBadNet() {
        setTipInfo(badNetResId, badNetText, true);
    }
    
    public void showWait() {
        showProgress();
    }
    
    public void showSuccess() {
        showChild(true);
    }
    
    public void showError() {
        setTipInfo(errorImageResId, errorText, true);
    }
    
    public void showEmpty() {
        setTipInfo(emptyImageResId, emptyText, false);
    }
    
    public void showTip(int customImageResId, String text) {
        showTip(customImageResId, text, false);
    }
    
    public void showTip(int customImageResId, String text, boolean isShowRetry) {
        setTipInfo(customImageResId, text, isShowRetry);
    }
    
    private void setTipInfo(int resId, String text, boolean isShowRetry) {
        ImageView imgViewHolderImage = tipView.findViewById(R.id.img_viewHolder_image);
        TextView txtViewHolderTip = tipView.findViewById(R.id.txt_viewHolder_tip);
        ProgressBar psgViewHolderProgress = tipView.findViewById(R.id.psg_viewHolder_progress);
        TextView btnViewHolderRetry = tipView.findViewById(R.id.btn_viewHolder_retry);
        
        if (themeColor!=-1) {
            imgViewHolderImage.setColorFilter(themeColor);
            txtViewHolderTip.setTextColor(themeColor);
        }
        
        imgViewHolderImage.setVisibility(VISIBLE);
        txtViewHolderTip.setVisibility(VISIBLE);
        psgViewHolderProgress.setVisibility(GONE);
        if (isShowRetry && showRetryButton) {
            btnViewHolderRetry.setText(retryButtonText);
            btnViewHolderRetry.setVisibility(VISIBLE);
        } else {
            btnViewHolderRetry.setVisibility(GONE);
        }
        
        btnViewHolderRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                if (onRetryButtonClickListener!=null){
                    onRetryButtonClickListener.onClick(v);
                }
            }
        });
        
        imgViewHolderImage.setImageResource(resId);
        txtViewHolderTip.setText(text);
        
        showChild(false);
    }
    
    private void showProgress() {
        ImageView imgViewHolderImage = tipView.findViewById(R.id.img_viewHolder_image);
        TextView txtViewHolderTip = tipView.findViewById(R.id.txt_viewHolder_tip);
        ProgressBar psgViewHolderProgress = tipView.findViewById(R.id.psg_viewHolder_progress);
        TextView btnViewHolderRetry = tipView.findViewById(R.id.btn_viewHolder_retry);
        
        imgViewHolderImage.setVisibility(GONE);
        txtViewHolderTip.setVisibility(GONE);
        psgViewHolderProgress.setVisibility(VISIBLE);
        btnViewHolderRetry.setVisibility(GONE);
        
        showChild(false);
    }
    
    private void refreshViews() {
        childViews = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView != tipView) {
                childViews.add(childView);
            }
        }
    }
    
    private void showChild(boolean isShow) {
        for (View child : childViews) {
            if (isShow) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
        if (isShow) {
            tipView.setVisibility(GONE);
        } else {
            tipView.setVisibility(VISIBLE);
        }
    }
    
    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        refreshViews();
    }
    
    private String getResString(TypedArray typedArray, int id, String defaultStr) {
        String cache = typedArray.getString(id);
        if (cache == null) {
            return defaultStr;
        } else {
            return cache;
        }
    }
    
    public OnRetryButtonClickListener getOnRetryButtonClickListener() {
        return onRetryButtonClickListener;
    }
    
    public HolderView setOnRetryButtonClickListener(OnRetryButtonClickListener onRetryButtonClickListener) {
        this.onRetryButtonClickListener = onRetryButtonClickListener;
        return this;
    }
    
    @Override
    public void setOnClickListener(View.OnClickListener l) {
        if (tipView!=null){
            tipView.setOnClickListener(l);
        }
    }
}
