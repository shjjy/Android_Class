package lesson.android.yangchang.lesson6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyGraphView extends View {

    Paint paint = new Paint();
    Path path = new Path();

    public MyGraphView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF pointF = new PointF();
        pointF.set(event.getX(), event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointF.x, pointF.y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointF.x, pointF.y);
                break;
            default:
                return false;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        //        canvas.drawLine(10, 10, 100, 100, paint);

        ArrayList<Float> list = new ArrayList<>();
        list.add((float) 370);
        list.add((float) 26);
        list.add((float) 570);
        list.add((float) 366);

        list.add((float) 570);
        list.add((float) 366);
        list.add((float) 253);
        list.add((float) 154);

        list.add((float) 253);
        list.add((float) 154);
        list.add((float) 539);
        list.add((float) 122);

        list.add((float) 539);
        list.add((float) 122);
        list.add((float) 300);
        list.add((float) 480);

        list.add((float) 300);
        list.add((float) 480);
        list.add((float) 370);
        list.add((float) 26);

        float[] star = new float[20];
        for(int i=0;i<star.length;i++){
            star[i] = list.get(i);
        }
        canvas.drawLines(star, paint);

        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
