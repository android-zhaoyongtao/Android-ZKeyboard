package com.zyt.keyboard.utiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zyt.keyboard.MContext;

/**
 * Created by dsr on 16/8/12.
 */
@SuppressLint("AppCompatCustomView")
public class TextRoundBothView extends TextView {
    private Path path;
    private Paint paint = new Paint();
    private RectF rectF = new RectF();
    private int back;
    private int edge;
    private int edgeSelect;
    private int backSelect;
    private float stroke;

    public TextRoundBothView(Context context) {
        super(context);
        init();
    }


    public TextRoundBothView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextRoundBothView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        stroke = MContext.DP1 * 0.5f;
        paint.setStrokeWidth(stroke);
        setFocusableInTouchMode(false);//是否通过touch来获取聚焦，若为true，第一次是获取焦点，第二次才相应click事件，为false，则直接响应
    }

    public void setBackground(int edge, int back) {
        this.edge = edge;
        this.back = back;
        invalidate();
    }

    public void setBackgroundSelect(int edge, int back) {
        this.edgeSelect = edge;
        this.backSelect = back;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (path == null) {
            path = new Path();
        } else {
            path.reset();
        }
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();
        float round = getMeasuredHeight() / 2 - stroke;
        path.moveTo(round + stroke, stroke);
        path.lineTo(width - round - stroke, stroke);
        rectF.left = width - height - stroke;
        rectF.top = stroke;
        rectF.right = width - stroke;
        rectF.bottom = height - stroke;
        path.arcTo(rectF, -90, 180, false);
        path.lineTo(width - round - stroke, height - stroke);
        path.lineTo(round + stroke, height - stroke);
        rectF.left = stroke;
        rectF.top = stroke;
        rectF.right = height - stroke;
        rectF.bottom = height - stroke;
        path.arcTo(rectF, 90, 180, false);
        path.lineTo(round + stroke, stroke);
        path.close();
        paint.setColor(isSelected() ? backSelect : back);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        paint.setColor(isSelected() ? edgeSelect : edge);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
