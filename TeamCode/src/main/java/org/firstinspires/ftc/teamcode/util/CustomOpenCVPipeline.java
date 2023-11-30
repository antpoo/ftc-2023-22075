package org.firstinspires.ftc.teamcode.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class CustomOpenCVPipeline extends OpenCvPipeline {

    final boolean RED = false, BLUE = true;
    boolean colour;
    public String whichSide;
    public boolean hasProcessedFrame = false;
    public int max;

    ArrayList<double[]> frameList;
    public static double strictLowS = 140;
    public static double strictHighS = 255;

    public CustomOpenCVPipeline(boolean colour) {
        frameList = new ArrayList<>();
        this.colour = colour;
        rectColor = colour == RED ? new Scalar(255.0, 0.0, 0.0) : new Scalar(0.0, 0.0, 255.0);
        rectColorFound = colour == RED ? new Scalar(255.0, 100.0, 100.0) : new Scalar(100.0, 100.0, 255.0);
    }

    Mat YCbCr = new Mat();
    Mat region1_Cb, region2_Cb, region3_Cb;
    Mat outPut = new Mat();
    Scalar rectColor;
    Scalar rectColorFound;
    Mat Cb = new Mat();
    public int avg1, avg2, avg3;

    void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCbCr, Cb, 2);
    }

    public String getWhichSide() {
        return whichSide;
    }

    @Override
    public void init(Mat firstFrame) {
        inputToCb(firstFrame);

        Rect leftRect = new Rect(2, 2, 416, 718);
        Rect centerRect = new Rect(418, 2, 416, 718);
        Rect rightRect = new Rect(837, 2, 416, 718);

//        leftRect = new Rect(
//                new Point(0, 0),
//                new Point(425, 720)
//        );
//
//        centerRect = new Rect(
//                new Point(426, 0),
//                new Point(851, 720)
//        );
//
//        rightRect= new Rect(
//                new Point(853, 0),
//                new Point(1280, 720)
//        );

        region1_Cb = Cb.submat(leftRect);
        region2_Cb = Cb.submat(centerRect);
        region3_Cb = Cb.submat(rightRect);
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat mat = new Mat();

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if (mat.empty()) {
            return input;
        }

        Scalar lowHSV = colour == RED ? new Scalar(0, 50, 50) : new Scalar(90, 50, 70);
        Scalar highHSV = colour == RED ? new Scalar(30, 255, 255) : new Scalar(128, 255, 255);

        Mat thresh = new Mat();
        Core.inRange(mat, lowHSV, highHSV, thresh);

        Mat masked = new Mat();
        Core.bitwise_and(mat, mat, masked, thresh);
        Scalar average = Core.mean(masked, thresh);

        Mat scaledMask = new Mat();
        masked.convertTo(scaledMask, -1, 150 / average.val[1], 0);

        Mat scaledThresh = new Mat();

        Scalar strictLowHSV = colour == RED ? new Scalar(0, 100, 100) : new Scalar(100, 100, 100);
        Scalar strictHighHSV = colour == RED ? new Scalar(0, 255, 255) : new Scalar(150, 255, 255);

        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, scaledThresh);

        Mat finalMask = new Mat();
        Core.bitwise_and(mat, mat, finalMask, scaledThresh);

        Mat edges = new Mat();
        Imgproc.Canny(scaledThresh, edges, 100, 200);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(scaledThresh, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        if (frameList.size() > 5) {
            frameList.remove(0);
        }

        input.release();
        scaledThresh.copyTo(input);
        scaledThresh.release();
        scaledMask.release();
        mat.release();
        masked.release();
        edges.release();
        thresh.release();
        finalMask.release();

        Rect leftRect = new Rect(2, 2, 416, 718);
        Rect centerRect = new Rect(418, 2, 416, 718);
        Rect rightRect = new Rect(837, 2, 416, 718);

        Imgproc.rectangle(input, leftRect, rectColor, 2);
        Imgproc.rectangle(input, centerRect, rectColor, 2);
        Imgproc.rectangle(input, rightRect, rectColor, 2);

        Mat region1_bw = input.submat(leftRect);
        Mat region2_bw = input.submat(centerRect);
        Mat region3_bw = input.submat(rightRect);

        avg1 = (int) Core.countNonZero(region1_bw);
        avg2 = (int) Core.countNonZero(region2_bw);
        avg3 = (int) Core.countNonZero(region3_bw);

        region1_bw.release();
        region2_bw.release();
        region3_bw.release();

        int maxOneTwo = Math.max(avg1, avg2);
        max = Math.max(maxOneTwo, avg3);

        if (max == avg1) {
            whichSide = "left";
        } else if (max == avg2) {
            whichSide = "center";
        } else if (max == avg3) {
            whichSide = "right";
        }

        hasProcessedFrame = true;

        return input;
    }
}
