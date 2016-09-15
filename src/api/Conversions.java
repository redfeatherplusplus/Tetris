package api;

import java.awt.Dimension;
import java.awt.Point;

public class Conversions {
	private static float logicalWidth = 0.f;
	private static float logicalHeight = 0.f;
	private static float pixelSize = 0.f;
	private static int centerX = 0;
	private static int centerY = 0;
	
	public static void setLogicalDimensions(float logicalWidth, 
			float logicalHeight) {
		Conversions.logicalWidth = logicalWidth;
		Conversions.logicalHeight = logicalHeight;
	}
	
	public static void setDeviceDimensions(Dimension deviceSize) {
		int maxX = deviceSize.width - 1;
		int maxY = deviceSize.height - 1;
		
		Conversions.pixelSize = Math.max(
				logicalWidth / maxX, 
				logicalHeight / maxY);
		Conversions.centerX = maxX >> 1;
		Conversions.centerY = maxY >> 1;
	}
	
	public static int toDeviceX(float x) {
		return Math.round(centerX + x / pixelSize);
	}
	
	public static int toDeviceY(float y) {
		return Math.round(centerY - y / pixelSize);
	}
	
	public static float toLogicalX(float x) {
		return (x - centerX) * pixelSize;
	}
	
	public static float toLogicalY(float y) {
		return (centerY - y) * pixelSize;
	}
	
	public static Point toDevice(PointF point) {
		return new Point(toDeviceX(point.x), toDeviceY(point.y));
	}
	
	public static PointF toLogical(Point point) {
		return new PointF(toLogicalX(point.x), toLogicalY(point.y));
	}
}
