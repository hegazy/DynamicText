package com.abdallaadelessa.android.dynamictext.ui_components;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.abdallaadelessa.android.dynamictext.DynamicErrorHandler;
import com.abdallaadelessa.android.dynamictext.DynamicResources;
import com.abdallaadelessa.android.dynamictext.DynamicTextLoader;
import com.abdallaadelessa.android.dynamictext.DynamicTextManager;
import com.abdallaadelessa.android.dynamictext.R;

/**
 * Created by Abdalla on 28/11/2016.
 */

public class DynamicTextView extends TextView {
    private DynamicResources resources;

    public DynamicTextView(Context context) {
        super(context);
    }

    public DynamicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributeSet(context, attrs, -1);
    }

    public DynamicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributeSet(context, attrs, defStyleAttr);
    }

    @Override
    public DynamicResources getResources() {
        if(resources == null) {
            resources = new DynamicResources(super.getResources().getAssets(), super.getResources().getDisplayMetrics(), super.getResources().getConfiguration());
        }
        return resources;
    }

    // ----------------------->

    private void readAttributeSet(Context context, AttributeSet attrs, int defStyle) {
        try {
            TypedArray a;
            if(defStyle != -1) {
                a = context.obtainStyledAttributes(attrs, R.styleable.dynamicText, defStyle, 0);
            }
            else {
                a = context.obtainStyledAttributes(attrs, R.styleable.dynamicText);
            }
            String dynamicTextKey = a.getString(R.styleable.dynamicText_text);
            if(!TextUtils.isEmpty(dynamicTextKey)) {
                setDynamicText(dynamicTextKey);
            }
            a.recycle();
        }
        catch(Exception e) {
            DynamicErrorHandler.onError(e);
        }
    }

    public void setDynamicText(final String key) {
        setText(getResources().getString(key));
    }

    // ----------------------->

}
