package art_generator;

import java.util.ArrayList;

public class ArtGeneratorPlayground {
	public static void main(String[] args) {
		ArtGeneratorImpl x = new ArtGeneratorImpl(10, 10);
//		
//		System.out.println("\n" + "origin point test:");
//		x.originPointTestImpl();
//		System.out.println(x.saveCurrentImage("originPointTest"));
//		
		x.newImage(500, 500);
//		
//		System.out.println("\n" + "box art:");
//		x.drawBoxArt();
//		System.out.println(x.saveCurrentImage("boxArt"));
//		
//		System.out.println("\n" + "rainbow box art:");
//		x.rainbowBoxImpl();
//		System.out.println(x.saveCurrentImage("rainbowBoxArt"));
//		
//		System.out.println("\n" + "circle art:");
//		x.circleArtImpl();
//		System.out.println(x.saveCurrentImage("circleArt"));
//		
//		System.out.println("\n" + "dark gradient:");
//		x.darkGradientImpl();
//		System.out.println(x.saveCurrentImage("darkGradient"));
//		
//		System.out.println("\n" + "light gradient:");
//		x.lightGradientImpl();
//		System.out.println(x.saveCurrentImage("lightGradient"));
//		
//		System.out.println("\n" + "custom gradient:");
//		ArrayList<OriginPointImpl> pointList = new ArrayList<OriginPointImpl>();
//		pointList.add(new OriginPointImpl(0, 0, 27, 13, 255, 255));
//		pointList.add(new OriginPointImpl(499, 499, 0, 255, 255, 255));
//		pointList.add(new OriginPointImpl(499, 0, 200, 0, 200, 255));
//		pointList.add(new OriginPointImpl(0, 499, 255, 100, 100, 255));
//		x.printOriginPoints(pointList);
//		x.fillBlack();
//		x.gradient(pointList);
//		System.out.println(x.saveCurrentImage("customGradient"));
		
		System.out.println("\n" + "vivid gradient:");
		x.vividGradientImpl();
		System.out.println(x.saveCurrentImage("vividGradient"));
		
//		ArtGeneratorImpl y = new ArtGeneratorImpl(500, 500);
//		System.out.println("\n" + "custom super gradient:");
//		ArrayList<OriginPointImpl> pointList = new ArrayList<OriginPointImpl>();
//		pointList.add(new OriginPointImpl(0, 0, 0, 255, 0, 255, 4));
//		pointList.add(new OriginPointImpl(499, 499, 0, 0, 255, 255, 4));
//		pointList.add(new OriginPointImpl(499, 0, 200, 0, 200, 255, 2));
//		pointList.add(new OriginPointImpl(0, 499, 255, 100, 100, 255, 1));
//		y.printOriginPoints(pointList);
//		y.drawSuperGradient(pointList);
//		System.out.println(y.saveCurrentImage("customSuperGradient"));
	}
}
