package com.lingzhizhuxuanlv.springboot.util;

import java.util.ArrayList;
import java.util.List;

public class LocationUtil {

    public static void main(String[] args) {
        String rectangleString = "37.816251,112.502146;37.816217,112.504581;37.814497,112.504678;37.814615,112.501845";
        System.out.println(inRectangle("37.815573", "112.503905", rectangleString));
    }

    public static boolean inRectangle(String x, String y, String rectangleString){
        String[] rectanglePoints = rectangleString.split(";");
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < rectanglePoints.length; i++) {
            String[] points = rectanglePoints[i].split(",");
            pointList.add(new Point(Double.parseDouble(points[0]), Double.parseDouble(points[1])));
        }
        Point[] ps = new Point[pointList.size()];
        ps = pointList.toArray(ps);
        return isPtInPoly(Double.parseDouble(x), Double.parseDouble(y), ps);
    }

    public static boolean isPtInPoly(double ALon ,double ALat ,Point[] ps) {
        int iSum, iCount, iIndex;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (ps.length < 3) {
            return false;
        }
        iSum = 0;
        iCount = ps.length;
        for (iIndex = 0; iIndex<iCount; iIndex++) {
            if (iIndex == iCount - 1) {
                dLon1 = ps[iIndex].getX();
                dLat1 = ps[iIndex].getY();
                dLon2 = ps[0].getX();
                dLat2 = ps[0].getY();
            } else {
                dLon1 = ps[iIndex].getX();
                dLat1 = ps[iIndex].getY();
                dLon2 = ps[iIndex + 1].getX();
                dLat2 = ps[iIndex + 1].getY();
            }
            //以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到A点向左射线与边的交点的x坐标
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);
                    //如果交点在A点左侧（说明是做射线与边的交点），则射线与边的全部交点数加一
                    if (dLon < ALon) {
                        iSum++;
                    }
                }
            }
        }
        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }

}
