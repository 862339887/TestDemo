package com.example.alertdialogtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.alertdialogtest.R;

public class HorizontalProgressBar extends ProgressBar {
    private  static final int DEFAULT_TEXT_SIZE = 10 ; // sp
    private  static final int DEFAULT_TEXT_COLOR = 0xFFFc00d1 ;
    private  static final int DEFAULT_UNREACH_COLOR = 0xffd3d6da ;
    private  static final int DEFAULT_UNREACH_HEIGHT = 2 ; // dp
    private  static final int DEFAULT_REACH_COLOR = DEFAULT_TEXT_COLOR ;
    private  static final int DEFAULT_REACH_HEIGHT= 3 ; // dp
    private  static final int DEFAULT_TEXT_OFFSET = 10 ; // dp


    //使用默认值
    private  float mTextSize = (float)sp2px(DEFAULT_TEXT_SIZE) ;
    private int mTextColor = DEFAULT_TEXT_COLOR ;
    private int mUnreachColor = DEFAULT_UNREACH_COLOR;
    private int mUnreachHeight = dp2px(DEFAULT_UNREACH_HEIGHT);
    private int mReachColor = DEFAULT_REACH_COLOR ;
    private int mReachHeight = dp2px(DEFAULT_REACH_HEIGHT);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET) ;
    public HorizontalProgressBar(Context context) {
        super(context);
    }
    private Paint mPaint = new Paint();

    private  int mRealWidth ;

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getStyleAttrs(attrs);
    }

    private void getStyleAttrs(AttributeSet attrs){
        //获取自定义属性
        TypedArray ta = getContext().obtainStyledAttributes(attrs,
                R.styleable.HorizontalProgressBar);
        mTextSize = ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textSize,
                mTextSize);
        mTextColor =  ta.getColor(R.styleable.HorizontalProgressBar_hpb_textColor,
                mTextColor) ;
        mUnreachColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_unReachColor,
                mUnreachColor);
        mUnreachHeight = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_unReachHeight,
                mUnreachHeight);
        mReachColor =  ta.getColor(R.styleable.HorizontalProgressBar_hpb_reachColor,
                mReachColor);
        mReachHeight = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_reachHeight,
                mReachHeight);
        mTextOffset = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textOffset,
                mTextOffset);

        ta.recycle();

        //设置字体大小 ，用于后面计算控件的高度
        mPaint.setTextSize(mTextSize);
    }
    private int dp2px(int dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal,getResources().getDisplayMetrics());
    }


    /**
     *
     * @param spVal
     * @return
     */
    private int sp2px(int spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal,getResources().getDisplayMetrics());
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);


        //获取宽度的值,通过宽度的模式来获取 宽度的值
        int widthVal = MeasureSpec.getSize(widthMeasureSpec);
        //获取控件高度的值
        int height = getHeightMeasure(heightMeasureSpec);

        //设置控件的尺寸
        setMeasuredDimension(widthVal,height);

        //设置控件的实际宽度
        mRealWidth = getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
    }
    private int getHeightMeasure(int heightMeasureSpec) {

        int result = 0 ;

        //获得测量模式
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量结果--高度
        int size = MeasureSpec.getSize(heightMeasureSpec);
        //用户的提供的是精确的数值模式
        if(mode==MeasureSpec.EXACTLY){

            result = size ;
        }else{
            //下部分的之减去上部分的值
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            //
            result = getPaddingTop()+getPaddingBottom()+
                    Math.max(Math.abs(textHeight),Math.max(mReachHeight,mUnreachHeight));

            if(mode==MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result ;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        //绘制其实坐标
        canvas.translate(getPaddingLeft(),getHeight()/2);

        // 代表是否需要绘制onReachbar
        boolean isNeedUnReach = false ;
        float radio =  getProgress()*1.0f/getMax() ;
        //测量文本宽度
        String text = getProgress()+"%" ;
        int  textWidth = (int) mPaint.measureText(text);

        //
        float progressX = radio*mRealWidth ;
        //保证数字被显示
        if(progressX+textWidth>mRealWidth){

            progressX = mRealWidth - textWidth ;
            isNeedUnReach = true ;
        }

        //绘制进度条的进度长度

        float endX = progressX - mTextOffset/2 ;

        if(endX>0){
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);

            canvas.drawLine(0,0,endX, 0, mPaint);
        }

        //绘制文本
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
        int y = (int) (-(mPaint.descent()+mPaint.ascent())/2);
        canvas.drawText(text,progressX,y,mPaint);

        //绘制unReachbar
        if (!isNeedUnReach){
            float  start = progressX + mTextOffset/2 + textWidth ;
            mPaint.setColor(mUnreachColor);
            mPaint.setStrokeWidth(mUnreachHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }
        canvas.restore();
    }


}
