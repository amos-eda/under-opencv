package org.opencv.samples.colorblobdetect;

import java.util.ArrayList;
import java.util.List;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class ColorBlobDetectionActivity extends Activity  {
    private static final String  TAG              = "OCVSample::Activity";

    private boolean              mIsColorSelected = false;private int PolygonTest;
    private Mat                  mRgba,mPyrDownMat,mHsvMat;
    private Scalar               mBlobColorRgba;
    private Scalar               mBlobColorHsv;
    private ColorBlobDetector    mDetector;
    private Mat                  mSpectrum,h ;
    private Size                 SPECTRUM_SIZE;
    private Scalar               CONTOUR_COLOR;
    private MatOfPoint2f           mMOP2f1;
    private Bitmap                  bMap,bmap1;


    private CameraBridgeViewBase mOpenCvCameraView;
    private int x1,y1;




    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    bMap=BitmapFactory.decodeResource(getResources(),R.drawable.icon);

                    Log.i(TAG, "OpenCV loaded successfully1");

                    Log.i(TAG, "OpenCV loaded successfully2");
                    jules();



                    /*mOpenCvCameraView.enableView();
                    mOpenCvCameraView.setOnTouchListener(ColorBlobDetectionActivity.this);*/
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public ColorBlobDetectionActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.color_blob_detection_surface_view);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);



        /*mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.color_blob_detection_activity_surface_view);
        mOpenCvCameraView.setCvCameraViewListener(this);*/
    }
    public void jules(){
        h=new Mat(70, 70, CvType.CV_8UC4);
        CONTOUR_COLOR = new Scalar(255,0,0,255);
        Utils.bitmapToMat(bMap,h);

        Utils.matToBitmap(h,bMap);

        mBlobColorRgba = new Scalar(255);
        mBlobColorHsv = new Scalar(255);


        Log.i(TAG, "OpenCV loaded successfully3");



        ImageView iv = (ImageView)findViewById((R.id.imageView));
        iv.setImageBitmap(bMap);}


    public void onPause()
    {
        super.onPause();
        /*if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    */}


   /* public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
    }

    /*public void onDestroy() {
        super.onDestroy();
        /*if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
 */

    /* public void onCameraViewStarted(int width, int height) {
         mRgba = new Mat(height, width, CvType.CV_8UC4);
         //reason why you cant use any matrix , you have to use matrix with specifies height and width
         mDetector = new ColorBlobDetector();
         mSpectrum = new Mat();
         mBlobColorRgba = new Scalar(255);
         mBlobColorHsv = new Scalar(255);
         SPECTRUM_SIZE = new Size(200, 64);
         CONTOUR_COLOR = new Scalar(255,0,0,255);
         }

    */ public void onCameraViewStopped() {
        mRgba.release();
    }
    /*public boolean onTouch1(View v, MotionEvent event) { int cols = mRgba.cols();
        int rows = mRgba.rows();

        int xOffset = (mOpenCvCameraView.getWidth() - cols) / 2;
        int yOffset = (mOpenCvCameraView.getHeight() - rows) / 2;

        int x = (int)event.getX()- xOffset ;
        mDetector.process(mRgba);
        List<MatOfPoint> contours = mDetector.getContours();
        int y = (int)event.getY()-yOffset ;
        if(contours.size()>0){
            for (int i = 0; i < contours.size(); i++)
            {MatOfPoint2f  NewMtx = new MatOfPoint2f( contours.get(i).toArray() );
                //Convert contours(i) from MatOfPoint to MatOfPoint2f
                double j=Imgproc.pointPolygonTest(NewMtx,new Point(x,y),false);
                Core.putText(mRgba, "" + i, new Point(x, y), 3, 1, new Scalar(255, 0, 0, 255), 2);
            }
        }return false;}*/
    /*public boolean onTouch(View v, MotionEvent event) {
        int cols = mRgba.cols();
        int rows = mRgba.rows();

        int xOffset = (mOpenCvCameraView.getWidth() - cols) / 2;
        int yOffset = (mOpenCvCameraView.getHeight() - rows) / 2;

        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;
        x1 = x;
        y1 = y;

        Toast.makeText(this,"selected x and y succesful hell yeah! :P", Toast.LENGTH_LONG
        );
       if (mIsColorSelected==false) {
        Log.i(TAG, "Touch image coordinates: (" + x + ", " + y + ")");

        if ((x < 0) || (y < 0) || (x > cols) || (y > rows)) return false;

        Rect touchedRect = new Rect();

        touchedRect.x = (x > 4) ? x - 4 : 0;
        touchedRect.y = (y > 4) ? y - 4 : 0;

        touchedRect.width = (x + 4 < cols) ? x + 4 - touchedRect.x : cols - touchedRect.x;
        touchedRect.height = (y + 4 < rows) ? y + 4 - touchedRect.y : rows - touchedRect.y;

        Mat touchedRegionRgba = mRgba.submat(touchedRect);

        Mat touchedRegionHsv = new Mat();
        Imgproc.cvtColor(touchedRegionRgba, touchedRegionHsv, Imgproc.COLOR_RGB2HSV_FULL);

        // Calculate average color of touched region
        mBlobColorHsv = Core.sumElems(touchedRegionHsv);
        int pointCount = touchedRect.width * touchedRect.height;
        for (int i = 0; i < mBlobColorHsv.val.length; i++)
            mBlobColorHsv.val[i] /= pointCount;

        mBlobColorRgba = converScalarHsv2Rgba(mBlobColorHsv);

        Log.i(TAG, "Touched rgba color: (" + mBlobColorRgba.val[0] + ", " + mBlobColorRgba.val[1] +
                ", " + mBlobColorRgba.val[2] + ", " + mBlobColorRgba.val[3] + ")");

        mDetector.setHsvColor(mBlobColorHsv);

        Imgproc.resize(mDetector.getSpectrum(), mSpectrum, SPECTRUM_SIZE);

        mIsColorSelected = true;

        touchedRegionRgba.release();
        touchedRegionHsv.release();
    }
        return false; // don't need subsequent touch events
    }
public void jules (){Bitmap bMap=BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
    bmap=bMap.copy(Bitmap.Config.ARGB_8888,true);}*/
    /*public Mat onCameraFrame(CvCameraViewFrame inputFrame) {Log.i(TAG, "Touch image coordinates: 1");



mRgba=inputFrame.rgba();




        if (mIsColorSelected) {
            mDetector.process(mRgba);
            List<MatOfPoint> contours = mDetector.getContours();
            Log.i(TAG, "Touch image coordinates: 1");
            if(contours.size()>0){
                for (int i = 0; i < contours.size(); i++)
                {MatOfPoint2f  NewMtx = new MatOfPoint2f( contours.get(i).toArray() );
                    //Convert contours(i) from MatOfPoint to MatOfPoint2f
                    double j=Imgproc.pointPolygonTest(NewMtx,new Point(x1,y1),false);
                    Core.putText(mRgba, "" + i, new Point(x1, y1), 3, 1, new Scalar(255, 0, 0, 255), 2);
                }
            }



            Log.e(TAG, "Contours count: " + contours.size());
            Imgproc.drawContours(mRgba, contours, -1, CONTOUR_COLOR);

            Mat colorLabel = mRgba.submat(4, 68, 4, 68);
            colorLabel.setTo(mBlobColorRgba);

            Mat spectrumLabel = mRgba.submat(4, 4 + mSpectrum.rows(), 70, 70 + mSpectrum.cols());
            mSpectrum.copyTo(spectrumLabel);

       }

        return mRgba;
    }

    private Scalar converScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        Imgproc.cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_HSV2RGB_FULL, 4);

        return new Scalar(pointMatRgba.get(0, 0));
    }*/
    public void process_ntitled()
    {   mDetector.process(h);
        List<MatOfPoint> contours = mDetector.getContours();
        Imgproc.drawContours(h, contours, -1, CONTOUR_COLOR);
    }
    public void contour_evaluation()
    {
        Imgproc.pyrDown(h, mPyrDownMat);
        Log.i(TAG, "OpenCV loaded successfully9");
        Imgproc.pyrDown(mPyrDownMat, mPyrDownMat);
        Log.i(TAG, "OpenCV loaded successfully10");

        Imgproc.cvtColor(mPyrDownMat, mHsvMat, Imgproc.COLOR_RGB2HSV_FULL);

    }
}

