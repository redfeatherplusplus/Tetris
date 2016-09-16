package api;

import java.awt.Dimension;
import java.awt.Point;

public class Conversions {
	public static float logicalWidth = 0.f;
	public static float logicalHeight = 0.f;
	public static float logicalCenterX = 0.f;
	public static float logicalCenterY = 0.f;
	public static float pixelSize = 0.f;
	public static int deviceCenterX = 0;
	public static int deviceCenterY = 0;
	
	public static void setLogicalDimensions(float logicalWidth, 
			float logicalHeight) {
		Conversions.logicalWidth = logicalWidth;
		Conversions.logicalHeight = logicalHeight;
		Conversions.logicalCenterX = logicalWidth / 2.f;
		Conversions.logicalCenterY = logicalHeight / 2.f;
	}
	
	public static void setDeviceDimensions(Dimension deviceSize) {
		Conversions.pixelSize = Math.max(
				logicalWidth / deviceSize.width, 
				logicalHeight / deviceSize.width);
		
		Conversions.deviceCenterX = deviceSize.width >> 1;
		Conversions.deviceCenterY = deviceSize.width >> 1;
	}
	
	public static int toDeviceX(float x) {
		return Math.round(deviceCenterX - (x + logicalCenterX) / pixelSize);
	}
	
	public static int toDeviceY(float y) {
		return Math.round(deviceCenterY - (y + logicalCenterY) / pixelSize);
	}
	
	public static float toLogicalX(float x) {
		return (x - deviceCenterX) * pixelSize;
	}
	
	public static float toLogicalY(float y) {
		return (y- deviceCenterY) * pixelSize;
	}
	
	public static Point toDevice(PointF point) {
		return new Point(toDeviceX(point.x), toDeviceY(point.y));
	}
	
	public static PointF toLogical(Point point) {
		return new PointF(toLogicalX(point.x), toLogicalY(point.y));
	}
}
