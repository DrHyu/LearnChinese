package com.example.drhyu.learnchinese.MiscClasses;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.example.drhyu.learnchinese.MiscClasses.Character;

import java.util.List;

import io.apptik.widget.MultiSlider;

/**
 * Created by Jaume on 7/28/2016.
 */
public class MyMultiSlider extends MultiSlider {


    public List<Character> values;

    public MyMultiSlider(Context context) {
        super(context, null);
    }

    public MyMultiSlider(Context context, AttributeSet attrs) {
        super(context, attrs, io.apptik.widget.R.attr.multiSliderStyle);
    }

    public MyMultiSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle, 0);
    }

    public MyMultiSlider(Context context, AttributeSet attrs, int defStyle, int styleRes) {
        super(context, attrs, defStyle);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int margin = 30;
        for(int i =0; i < 2; i++){

            Rect thumbRect = getThumb(i).getThumb().getBounds();

            int x = thumbRect.left;
            int y = thumbRect.bottom ;

            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setTextSize(80);
            p.setTextAlign(Paint.Align.LEFT);

            if(values != null && values.size()> this.getThumb(i).getValue()) {
                String msg = values.get(getThumb(i).getValue()).getCharacter();

                Rect bounds = new Rect();
                p.getTextBounds(msg,0,msg.length(),bounds);
                int height = bounds.height();
                int width = bounds.width();
                float adjust = (40- width/2);

                if(x < Math.abs(adjust)+margin){adjust = margin;}
                if(x+ width + margin > getWidth()){adjust = getWidth()- (x+ width) -margin;}

                canvas.drawText(msg, x+adjust, y + 100, p);

                msg = Integer.toString(getThumb(i).getValue()+1);

                bounds = new Rect();
                p.getTextBounds(msg,0,msg.length(),bounds);
                height = bounds.height();
                width = bounds.width();

                if(x < margin){adjust = margin;}
                else if(x+ width + margin > getWidth()){adjust = getWidth()- (x+ width) -margin;}
                else {adjust =0;}

                canvas.drawText(msg, x+adjust, y + 20 , p);
            }
        }
    }

}
