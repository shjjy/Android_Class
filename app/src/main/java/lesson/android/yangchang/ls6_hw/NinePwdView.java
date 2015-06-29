package lesson.android.yangchang.ls6_hw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import lesson.android.yangchang.demo.R;

public class NinePwdView extends View {
    Context cxt;
    Paint linePaint = new Paint();
    Paint textPaint = new Paint();
    int width, height;

    Bitmap defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lock);
    int defBitMapD = defaultBitmap.getWidth();
    int defBitMapR = defBitMapD / 2;
    Bitmap selectedBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.indicator_lock_area);
    int selBitMapD = selectedBitMap.getWidth();
    int selBitMapR = selBitMapD / 2;

    NinePwdPoint[] points = new NinePwdPoint[9];

    int moveX, moveY;
    int startX = 0;
    int startY = 0;
    NinePwdPoint startPoint;

    boolean isUP = false;

    StringBuffer lockStrBuffer = new StringBuffer();


    public NinePwdView(Context context) {
        super(context);
        cxt = context;
        initPaint();
    }

    public NinePwdView(Context context, AttributeSet attrs){
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(30);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.MONOSPACE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getWidth();
        height = getHeight();
        if(width != 0 && height != 0){
            initPoints(points);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawNinePoint(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean doFlag = true;
        if(isUP){
            finish();
            doFlag = false;
        } else{
            doEvent(event);
            doFlag = true;
        }
        return doFlag;
    }

    private void drawNinePoint(Canvas canvas){
        if(startPoint != null){
            drawEachLine(canvas, startPoint);
        }
        for (NinePwdPoint point:points){
            if(point != null){
                if(point.isSelected()){
                    canvas.drawBitmap(selectedBitMap, point.getSelX(), point.getSelY(), null);
                }
                canvas.drawBitmap(defaultBitmap, point.getDefX(), point.getDefY(), null);
            }
        }
    }

    private void drawEachLine(Canvas canvas, NinePwdPoint ninePwdPoint){
        if(ninePwdPoint.hasNextId()){
            int nextId = ninePwdPoint.getNextId();
            drawLine(canvas, ninePwdPoint.getCenterX(), ninePwdPoint.getCenterY(),
                    points[nextId].getCenterX(), points[nextId].getCenterY());
            drawEachLine(canvas, points[nextId]);
        }
    }

    private void drawLine(Canvas canvas, float startX, float startY, float stopX, float stopY){
        canvas.drawLine(startX, startY, stopX, stopY, linePaint);
    }

    private void initPoints(NinePwdPoint[] points){
        int selSpace = (width - selBitMapD * 3) / 4;
        int selX = selSpace;
        int selY = height - width + selSpace;
        int defX = selX + selBitMapR - defBitMapR;
        int defY = selY + selBitMapR - defBitMapR;
        int pointsLen = points.length;
        for (int i = 0 ; i < pointsLen ; i++){
            if (i == 3 || i == 6) {
                selX = selSpace;
                selY += selBitMapD + selSpace;
                defX = selX + selBitMapR - defBitMapR;
                defY += selBitMapD + selSpace;
            }
            points[i] = new NinePwdPoint(i, defX, defY, selX, selY);
            selX += selBitMapD + selSpace;
            selY += selBitMapD + selSpace;
        }
    }

    private void finish(){
        for(NinePwdPoint point : points){
            point.setSelected(false);
            point.setNextId(point.getId());
        }
        lockStrBuffer.delete(0, lockStrBuffer.length());
        isUP = false;
        invalidate();
    }

    private void doEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                for(NinePwdPoint point:points){
                    if(point.isSelPoint(moveX, moveY) && point.isSelected() == false){
                        point.setSelected(true);
                        startX = point.getCenterX();
                        startY = point.getCenterY();
                        int lockStrLen = lockStrBuffer.length();
                        if(lockStrLen != 0){
                            int preId = lockStrBuffer.charAt(lockStrLen - 1) - 48;
                            points[preId].setNextId(point.getId());
                        }
                        lockStrBuffer.append(point.getId());
                        break;
                    }
                }
                invalidate(0, height - width, width, height);
                break;
            case MotionEvent.ACTION_DOWN:
                int downX = (int) event.getX();
                int downY = (int) event.getY();
                for(NinePwdPoint point : points){
                    if(point.isSelPoint(downX, downY)){
                        point.setSelected(true);
                        startPoint = point;
                        startX = point.getCenterX();
                        startY = point.getCenterY();
                        lockStrBuffer.append(point.getId());
                        break;
                    }
                }
                invalidate(0, height - width, width, height);
                break;
            case MotionEvent.ACTION_UP:
                startX = startY = moveX = moveY = 0;
                isUP = true;
                invalidate();
                save();
                break;
            default:
                break;
        }
    }

    private void save(){
        Intent intent = new Intent();
        SharedPreferences sharedPreferences = cxt.getSharedPreferences("GUE_PWD", 0);
        boolean isSetFirst = sharedPreferences.getBoolean("IS_SET_FIRST", false);
        if(isSetFirst){
            String pwd = this.getPwd();
            String first_pwd = sharedPreferences.getString("FIRST_PWD", "NOT SETTING");
            if(pwd.equals(first_pwd)){
                sharedPreferences.edit().clear().commit();
                sharedPreferences.edit().putBoolean("IS_SET", true).commit();
                sharedPreferences.edit().putString("GUE_PWD", pwd).commit();
                intent.setClass(cxt, SetPwdActivity.class);
            }else{
                sharedPreferences.edit().putBoolean("SECOND_ERROR", true).commit();
                intent.setClass(cxt, MainHW6Activity.class);
            }
        }else{
            sharedPreferences.edit().clear().commit();
            sharedPreferences.edit().putString("FIRST_PWD", this.getPwd()).commit();
            sharedPreferences.edit().putBoolean("IS_SET_FIRST", true).commit();
            intent.setClass(cxt, MainHW6Activity.class);
        }
        cxt.startActivity(intent);
        ((Activity)cxt).finish();
    }

    public String getPwd(){
        return lockStrBuffer.toString();
    }

    private class NinePwdPoint{
        private int id, nextId, defX, defY, selX, selY;
        private boolean isSelected;

        public int getId() {
            return id;
        }

        public int getDefX() {
            return defX;
        }

        public int getDefY() {
            return defY;
        }

        public int getSelX() {
            return selX;
        }

        public int getSelY() {
            return selY;
        }

        public boolean isSelected() {

            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public int getNextId() {
            return nextId;
        }

        public void setNextId(int nextId) {
            this.nextId = nextId;
        }

        public boolean hasNextId(){
            return nextId != id;
        }

        public int getCenterX(){
            return selX + selBitMapR;
        }

        public int getCenterY(){
            return selY + selBitMapR;
        }

        public NinePwdPoint(int id, int defX, int defY, int selX, int selY){
            this.id = this.nextId = id;
            this.defX = defX;
            this.defY = defY;
            this.selX = selX;
            this.selY = selY;
        }

        public boolean isSelPoint(int x, int y){
            boolean isX = x > selX && x < (selX + selBitMapD);
            boolean isY = y > selY && y < (selY + selBitMapD);
            return (isX && isY);
        }

    }
}
