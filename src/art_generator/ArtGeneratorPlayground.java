package art_generator;

import java.util.ArrayList;

public class ArtGeneratorPlayground {
	public static void main(String[] args) {
		ArtGeneratorImpl x = new ArtGeneratorImpl(10, 10);
		
//		System.out.println("\n" + "origin point test:");
//		x.originPointTest();
//		System.out.println(x.saveCurrentImage("originPointTest"));
		
		x.newImage(500, 500);
		
//		System.out.println("\n" + "box art:");
//		x.drawBoxArt();
//		System.out.println(x.saveCurrentImage("boxArt"));
		
		System.out.println("\n" + "rainbow box art:");
		x.drawRainbowBoxArt();
		System.out.println(x.saveCurrentImage("rainbowBoxArt"));
//		
//		System.out.println("\n" + "circle art:");
//		x.drawCircleArt();
//		System.out.println(x.saveCurrentImage("circleArt"));
//		
//		System.out.println("\n" + "dark gradient:");
//		x.drawDarkGradient();
//		System.out.println(x.saveCurrentImage("darkGradient"));
//		
//		System.out.println("\n" + "light gradient:");
//		x.drawLightGradient();
//		System.out.println(x.saveCurrentImage("lightGradient"));
//		
//		System.out.println("\n" + "custom gradient:");
//		ArrayList<OriginPointImpl> pointList = new ArrayList<OriginPointImpl>();
//		pointList.add(x.newOriginPoint(0, 0, 27, 13, 255, 255));
//		pointList.add(x.newOriginPoint(499, 499, 0, 255, 255, 255));
//		pointList.add(x.newOriginPoint(499, 0, 200, 0, 200, 255));
//		pointList.add(x.newOriginPoint(0, 499, 255, 100, 100, 255));
//		x.printOriginPoints(pointList);
//		x.blackClear();
//		x.drawGradient(pointList);
//		System.out.println(x.saveCurrentImage("customGradient"));
//		
//		System.out.println("\n" + "vivid gradient:");
//		x.drawVividGradient();
//		System.out.println(x.saveCurrentImage("vividGradient"));
	}
}
