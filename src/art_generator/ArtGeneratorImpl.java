package art_generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ArtGeneratorImpl {
	private BufferedImage img = null;

	public ArtGeneratorImpl(int x, int y) {
		this.newImage(x, y);
	}

	public BufferedImage getImage() {
		return img;
	}

	public void newImage(int x, int y) {
		/* in: x,y size of new image
		 * return: n/a
		 * effect: new blank image
		 */
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
	}

	public void drawBoxArt() {
		/* in: n/a
		 * return: n/a
		 * effect: draw box art on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 2) + 4);

		// draw gradient boxes
		this.whiteClear();
		this.drawBoxes(originPoints);
	}
	
	public void drawRainbowBoxArt() {
		/* in: n/a
		 * return: n/a
		 * effect: draw box art on image
		 */
		
		this.whiteClear();
		this.drawRainbowBox();
	}

	public void drawCircleArt() {
		/* in: n/a
		 * return: n/a
		 * effect: draw circleWaterColor on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw circles
		this.whiteClear();
		drawCircles(originPoints);
	}

	public void drawDarkGradient() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw gradient boxes
		this.blackClear();
		this.drawGradient(originPoints);
	}

	public void drawLightGradient() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw gradient boxes
		this.whiteClear();
		this.drawGradient(originPoints);
	}

	public void drawVividGradient() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random vivid gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw gradient boxes
		this.whiteClear();
		this.drawVividGradientCircles(originPoints);
	}

	public void blackClear() {
		this.pickColorClear(Color.black);
	}

	public void whiteClear() {
		this.pickColorClear(Color.white);
	}

	public void pickColorClear(Color color) {
		Graphics2D graphics  = img.createGraphics();
		graphics.setBackground(color);
		graphics.clearRect(0, 0, img.getWidth(), img.getHeight());
		graphics.dispose();
	}

	public void clear() {
		Graphics2D graphics  = img.createGraphics();
		graphics.clearRect(0, 0, img.getWidth(), img.getHeight());
		graphics.dispose();
	}

	public OriginPointImpl newOriginPoint(int x, int y, int r, int g, int b, int alpha) {
		return new OriginPointImpl(x, y, r, g, b, alpha);
	}

	public boolean saveCurrentImage(String fileName) {
		try {
			File outputfile = new File(fileName + ".png");
			ImageIO.write(this.getImage(), "png", outputfile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void originPointTest() {
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);
		this.whiteClear();
		Graphics2D graphics = img.createGraphics();
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			graphics.setColor(new Color(currentPoint.getColorR(), currentPoint.getColorG(), currentPoint.getColorB(), (int) (currentPoint.getColorAlpha())));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());
		}
		graphics.dispose();
	}

	public void drawCircles(ArrayList<OriginPointImpl> originPoints) {
		Graphics2D graphics  = img.createGraphics();
		for(int i = 0; i < originPoints.size(); i++) {
			OriginPointImpl currentPoint = originPoints.get(i);
			graphics.setColor(new Color(currentPoint.getColorR(), currentPoint.getColorG(), currentPoint.getColorB(), (int) currentPoint.getColorAlpha()));
			int circleDiameter;
			if (img.getHeight() > img.getWidth()) {
				circleDiameter = (int) img.getHeight()*2;
			} else {
				circleDiameter = (int) img.getWidth()*2;
			}
			graphics.fillOval(currentPoint.getX() - (circleDiameter/2), currentPoint.getY() - (circleDiameter/2), circleDiameter, circleDiameter);
		}
		graphics.dispose();
	}

	public void drawBoxes(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw boxes
		 */
		Graphics2D graphics  = img.createGraphics();

		int biggerSide = getBiggerSide();

		// origin points
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			graphics.setColor(new Color(currentPoint.getColorR(), currentPoint.getColorG(), currentPoint.getColorB(), (int) (currentPoint.getColorAlpha())));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());
			currentPoint.setColorAlpha(currentPoint.getColorAlpha() - (255/(biggerSide + 2)));
		}

		Color oldColor;
		int oldColorR;
		int oldColorG;
		int oldColorB;
		int oldColorAlpha;
		int rowLength = 3;
		int layerCount = 1;
		int currentX = (int) (img.getWidth()/2);
		int currentY = (int) (img.getHeight()/2);
		boolean imageHasWhite = true;
		int hasWhiteCount;

		// add layers
		while(imageHasWhite == true) { 
			hasWhiteCount = (rowLength-1) * 4 * originPoints.size();
			for(int a = 0; a < originPoints.size(); a++) { // add one layer to each gradient box
				OriginPointImpl currentPoint = originPoints.get(a);

				// top
				currentX = currentPoint.getX() - layerCount;
				currentY = currentPoint.getY() - layerCount;
				for(int b = 0; b < rowLength-1; b++) {
					if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						oldColorAlpha = oldColor.getAlpha();
						if (!(oldColorR == 255 && oldColorG == 255 && oldColorB == 255)) {
							hasWhiteCount--;
						}
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
								((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					} else {
						hasWhiteCount--;
					}
					currentX++;
				}

				// right
				currentX = currentPoint.getX() + layerCount;
				currentY = currentPoint.getY() - layerCount;
				for(int b = 0; b < rowLength-1; b++) {
					if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						oldColorAlpha = oldColor.getAlpha();
						if (!(oldColorR == 255 && oldColorG == 255 && oldColorB == 255)) {
							hasWhiteCount--;
						}
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
								((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					} else {
						hasWhiteCount--;
					}
					currentY++;
				}

				//bottom
				currentX = currentPoint.getX() + layerCount;
				currentY = currentPoint.getY() + layerCount;
				for(int b = 0; b < rowLength-1; b++) {
					if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						oldColorAlpha = oldColor.getAlpha();
						if (!(oldColorR == 255 && oldColorG == 255 && oldColorB == 255)) {
							hasWhiteCount--;
						}
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
								((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					} else {
						hasWhiteCount--;
					}
					currentX--;
				}

				// left
				currentX = currentPoint.getX() - layerCount;
				currentY = currentPoint.getY() + layerCount;
				for(int b = 0; b < rowLength-1; b++) {
					if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						oldColorAlpha = oldColor.getAlpha();
						if (!(oldColorR == 255 && oldColorG == 255 && oldColorB == 255)) {
							hasWhiteCount--;
						}
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
								((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					} else {
						hasWhiteCount--;
					}
					currentY--;
				}
				currentPoint.setColorAlpha(currentPoint.getColorAlpha() - (255/(biggerSide + 2)));
			}

			if (hasWhiteCount == 0) {
				imageHasWhite = false;
			}
			rowLength += 2;
			layerCount++;
		}

		//			// add some more layers
		//			int overlap = biggerSide/10;
		//			for (int a = 0; a < originPoints.size(); a++) {
		//				OriginPointImpl currentPoint = originPoints.get(a);
		//				currentPoint.setAlphaFade(currentPoint.getColorAlpha()/overlap);
		//			}
		//			for (int a = 0; a < overlap; a++) {
		//				for(int b = 0; b < originPoints.size(); b++) { // add one layer to each gradient box
		//					OriginPointImpl currentPoint = originPoints.get(b);
		//					currentPoint.setColorAlpha(currentPoint.getColorAlpha() - currentPoint.getAlphaFade());
		//					
		//					// top
		//					currentX = currentPoint.getX() - layerCount;
		//					currentY = currentPoint.getY() - layerCount;
		//					for(int c = 0; c < rowLength-1; c++) {
		//						if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
		//							oldColor = new Color(img.getRGB(currentX, currentY));
		//							oldColorR = oldColor.getRed();
		//							oldColorG = oldColor.getGreen();
		//							oldColorB = oldColor.getBlue();
		//							oldColorAlpha = oldColor.getAlpha();
		//							graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
		//									(int) ((currentPoint.getColorG() + oldColorG)/2),
		//									(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
		//									((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
		//							graphics.drawLine(currentX, currentY, currentX, currentY);
		//						}
		//						currentX++;
		//					}
		//
		//					// right
		//					currentX = currentPoint.getX() + layerCount;
		//					currentY = currentPoint.getY() - layerCount;
		//					for(int c = 0; c < rowLength-1; c++) {
		//						if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
		//							oldColor = new Color(img.getRGB(currentX, currentY));
		//							oldColorR = oldColor.getRed();
		//							oldColorG = oldColor.getGreen();
		//							oldColorB = oldColor.getBlue();
		//							oldColorAlpha = oldColor.getAlpha();
		//							graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
		//									(int) ((currentPoint.getColorG() + oldColorG)/2),
		//									(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
		//									((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
		//							graphics.drawLine(currentX, currentY, currentX, currentY);
		//						}
		//						currentY++;
		//					}
		//
		//					//bottom
		//					currentX = currentPoint.getX() + layerCount;
		//					currentY = currentPoint.getY() + layerCount;
		//					for(int c = 0; c < rowLength-1; c++) {
		//						if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
		//							oldColor = new Color(img.getRGB(currentX, currentY));
		//							oldColorR = oldColor.getRed();
		//							oldColorG = oldColor.getGreen();
		//							oldColorB = oldColor.getBlue();
		//							oldColorAlpha = oldColor.getAlpha();
		//							graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
		//									(int) ((currentPoint.getColorG() + oldColorG)/2),
		//									(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
		//									((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
		//							graphics.drawLine(currentX, currentY, currentX, currentY);
		//						}
		//						currentX--;
		//					}
		//
		//					// left
		//					currentX = currentPoint.getX() - layerCount;
		//					currentY = currentPoint.getY() + layerCount;
		//					for(int c = 0; c < rowLength-1; c++) {
		//						if (!(currentX < 0 || currentX >= img.getWidth() || currentY < 0 || currentY >= img.getHeight())) {
		//							oldColor = new Color(img.getRGB(currentX, currentY));
		//							oldColorR = oldColor.getRed();
		//							oldColorG = oldColor.getGreen();
		//							oldColorB = oldColor.getBlue();
		//							oldColorAlpha = oldColor.getAlpha();
		//							graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
		//									(int) ((currentPoint.getColorG() + oldColorG)/2),
		//									(int) ((currentPoint.getColorB() + oldColorB)/2), (int) 
		//									((currentPoint.getColorAlpha() + oldColorAlpha)/2)));
		//							graphics.drawLine(currentX, currentY, currentX, currentY);
		//						}
		//						currentY--;
		//					}
		//				}
		//				rowLength += 2;
		//				layerCount++;
		//			}
		graphics.dispose();
	}

	public void drawRainbowBox() {
		/* in: n/a
		 * out: n/a
		 * effect: draw rainbow box
		 */
		
		Graphics2D graphics  = img.createGraphics();

		// origin point
		Color color = new Color((int) Math.floor(Math.random() * 256),(int) Math.floor(Math.random() * 256),(int) Math.floor(Math.random() * 256));
		graphics.setColor(color);
		OriginPointImpl originPoint = new OriginPointImpl((int) (img.getWidth()/2), (int) (img.getHeight()/2));
		graphics.drawLine(originPoint.getX(), originPoint.getY(), originPoint.getX(), originPoint.getY());

		int rowLength = 3;
		int layerCount = 1;
		int biggerSide = this.getBiggerSide();
		int currentX = originPoint.getX();
		int currentY = originPoint.getY();

		// box layers
		for (int i = 0; i < (biggerSide-1)/2; i++) {
			
			// color
			color = new Color((int) Math.floor(Math.random() * 256),(int) Math.floor(Math.random() * 256),(int) Math.floor(Math.random() * 256));
			graphics.setColor(color);

			// top
			currentX = originPoint.getX() - layerCount;
			currentY = originPoint.getY() - layerCount;
			graphics.drawLine(currentX, currentY, currentX + rowLength-2, currentY);
			currentX += rowLength-1;

			// right
			graphics.drawLine(currentX, currentY, currentX, currentY + rowLength-2);
			currentY += rowLength-1;

			//bottom
			graphics.drawLine(currentX, currentY, currentX - rowLength-2, currentY);
			currentX -= rowLength-1;

			// left
			graphics.drawLine(currentX, currentY, currentX, currentY - rowLength-2);
			
			// adjust
			rowLength += 2;
			layerCount++;
		}
		graphics.dispose();
	}

	public void drawGradient(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw gradient circles
		 */
		Graphics2D graphics  = img.createGraphics();
		Color oldColor;
		int oldColorR;
		int oldColorG;
		int oldColorB;

		int biggerSide = getBiggerSide();

		// origin points
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			oldColor = new Color(img.getRGB(currentPoint.getX(), currentPoint.getY()));
			oldColorR = oldColor.getRed();
			oldColorG = oldColor.getGreen();
			oldColorB = oldColor.getBlue();
			graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
					(int) ((currentPoint.getColorG() + oldColorG)/2),
					(int) ((currentPoint.getColorB() + oldColorB)/2), (int) currentPoint.getColorAlpha()));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());

			// gradient
			int newColorAlpha;
			for (int currentX = 0; currentX < img.getWidth(); currentX++) {
				for(int currentY = 0; currentY < img.getHeight(); currentY++) {
					newColorAlpha = (int) (currentPoint.getColorAlpha() - ((currentPoint.getColorAlpha()/(biggerSide)) *
							Math.sqrt((Math.pow(currentX-currentPoint.getX(), 2) + Math.pow(currentY-currentPoint.getY(), 2)))));
					if(!(newColorAlpha < 0 || (currentPoint.getX() == currentX && currentPoint.getY() == currentY))) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), newColorAlpha));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					}
				}
			}
		}
		graphics.dispose();	
	}

	public void drawVividGradientCircles(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw vivid gradient circles
		 */
		Graphics2D graphics  = img.createGraphics();
		Color oldColor;
		int oldColorR;
		int oldColorG;
		int oldColorB;

		int biggerSide = getBiggerSide();

		// fill with first color
		OriginPointImpl firstPoint = originPoints.get(0);
		this.pickColorClear(new Color(firstPoint.getColorR(), firstPoint.getColorG(), firstPoint.getColorB(), (int) firstPoint.getColorAlpha()));
		originPoints.remove(0);

		// origin points
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			oldColor = new Color(img.getRGB(currentPoint.getX(), currentPoint.getY()));
			oldColorR = oldColor.getRed();
			oldColorG = oldColor.getGreen();
			oldColorB = oldColor.getBlue();
			graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
					(int) ((currentPoint.getColorG() + oldColorG)/2),
					(int) ((currentPoint.getColorB() + oldColorB)/2), (int) currentPoint.getColorAlpha()));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());

			// gradient
			int newColorAlpha;
			for (int currentX = 0; currentX < img.getWidth(); currentX++) {
				for(int currentY = 0; currentY < img.getHeight(); currentY++) {
					newColorAlpha = (int) (currentPoint.getColorAlpha() - ((currentPoint.getColorAlpha()/(biggerSide)) *
							Math.sqrt((Math.pow(currentX-currentPoint.getX(), 2) + Math.pow(currentY-currentPoint.getY(), 2)))));
					if(!(newColorAlpha < 0 || (currentPoint.getX() == currentX && currentPoint.getY() == currentY))) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), newColorAlpha));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					}
				}
			}
		}
		graphics.dispose();	
	}
	
	public void drawSuperGradient(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw super radial gradients
		 */
		
		Graphics2D graphics  = img.createGraphics();
		Color oldColor;
		int oldColorR;
		int oldColorG;
		int oldColorB;
		int biggerSide = getBiggerSide();

		// fill with first color
		OriginPointImpl currentPoint = originPoints.get(0);
		this.pickColorClear(new Color(currentPoint.getColorR(), currentPoint.getColorG(), currentPoint.getColorB()));

		// origin points
		for(int i = 1; i < originPoints.size(); i++) {
			currentPoint = originPoints.get(i);

			// origin pixel
			oldColor = new Color(img.getRGB(currentPoint.getX(), currentPoint.getY()));
			oldColorR = oldColor.getRed();
			oldColorG = oldColor.getGreen();
			oldColorB = oldColor.getBlue();
			graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
					(int) ((currentPoint.getColorG() + oldColorG)/2),
					(int) ((currentPoint.getColorB() + oldColorB)/2)));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());
			
			// gradient
			double distanceFromOrigin;
			double fadePoint;
			int newColorR;
			int newColorG;
			int newColorB;
			// for every pixel
			for (int currentX = 0; currentX < img.getWidth(); currentX++) {
				for(int currentY = 0; currentY < img.getHeight(); currentY++) {
					distanceFromOrigin = Math.sqrt((Math.pow(currentX-currentPoint.getX(), 2) + Math.pow(currentY-currentPoint.getY(), 2)));
					fadePoint = biggerSide * currentPoint.getStrength() / 10;
					if (distanceFromOrigin > fadePoint) { // fade
						newColorR = (int) (currentPoint.getColorR() - (currentPoint.getColorR() / fadePoint *
								distanceFromOrigin));
						newColorG = (int) (currentPoint.getColorG() - (currentPoint.getColorG() / fadePoint *
								distanceFromOrigin));
						newColorB = (int) (currentPoint.getColorB() - (currentPoint.getColorB() / fadePoint *
								distanceFromOrigin));
						if(!(newColorR < 0 || (currentPoint.getX() == currentX && currentPoint.getY() == currentY))) {
							oldColor = new Color(img.getRGB(currentX, currentY));
							oldColorR = oldColor.getRed();
							oldColorG = oldColor.getGreen();
							oldColorB = oldColor.getBlue();
							graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
									(int) ((currentPoint.getColorG() + oldColorG)/2),
									(int) ((currentPoint.getColorB() + oldColorB)/2), newColorAlpha));
							graphics.drawLine(currentX, currentY, currentX, currentY);
						}
					} else { // solid
						
					}
				}
			}
			
		}
		graphics.dispose();	
	}

	public void printOriginPoints(ArrayList<OriginPointImpl> originPoints) {
		for(int i = 0; i < originPoints.size(); i++) {
			OriginPointImpl currentPoint = originPoints.get(i);
			System.out.println("Point" + i + ": (x, y): (" + currentPoint.getX() + ", " + 
					currentPoint.getY() + ") strength: " + currentPoint.getStrength() + "\n    R:" + currentPoint.getColorR() + " G:" + currentPoint.getColorG() + " B:" + currentPoint.getColorB() + " Alpha:" + currentPoint.getColorAlpha());
		}
		System.out.print("\n");
	}

	// helper methods

	private ArrayList<OriginPointImpl> selectOriginPoints(int numOfOriginPoints) {
		/* in: number of origin points to make
		 * out: list of origin points
		 * effect: make list of origin points
		 */

		ArrayList<OriginPointImpl> originPoints = new ArrayList<OriginPointImpl>();
		boolean onSector1 = ((int) (Math.random()*2)) == 0;
		int currentSide;
		/* sector 1
		 * 0: bottom right
		 * 1: right
		 * 2: top right
		 * 3: top
		 * sector 2
		 * 4: top left
		 * 5: left
		 * 6: bottom left
		 * 7: bottom
		 */
		ArrayList<Integer> sector1 = new ArrayList<Integer>();
		sector1.add(0);
		sector1.add(1);
		sector1.add(2);
		sector1.add(3);
		ArrayList<Integer> sector2 = new ArrayList<Integer>();
		sector2.add(4);
		sector2.add(5);
		sector2.add(6);
		sector2.add(7);

		// Select semi-random points on border
		for (int i = 0; i < numOfOriginPoints; i++) {
			if(onSector1) {
				currentSide = (int) (Math.random() * sector1.size());
				if (sector1.get(currentSide) == 0) {
					// bottom right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1, 
							img.getHeight() - 1, (int) (Math.random() * 5)));
				} else if(sector1.get(currentSide) == 1) {
					// right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1,
							(int) ((Math.random()*(img.getHeight()*.40)) + img.getHeight()*.30), (int) (Math.random() * 5)));
				} else if(sector1.get(currentSide) == 2) {
					// top right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1,
							0, (int) (Math.random() * 5)));
				} else {
					// top
					originPoints.add(new OriginPointImpl((int) ((Math.random()*(img.getWidth()*.40)) + img.getWidth()*.30),
							0, (int) (Math.random() * 5)));
				}
				sector1.remove(currentSide);
				onSector1 = false;
			} else {
				currentSide = (int) (Math.random() * sector2.size());
				if (sector2.get(currentSide) == 4) {
					// top left
					originPoints.add(new OriginPointImpl(0, 
							0, (int) (Math.random() * 5)));
				} else if(sector2.get(currentSide) == 5) {
					// left
					originPoints.add(new OriginPointImpl(0,
							(int) ((Math.random()*(img.getHeight()*.40)) + img.getHeight()*.30), (int) (Math.random() * 5)));
				} else if(sector2.get(currentSide) == 6) {
					// bottom left
					originPoints.add(new OriginPointImpl(0,
							img.getHeight() - 1, (int) (Math.random() * 5)));
				} else {
					// bottom
					originPoints.add(new OriginPointImpl((int) ((Math.random()*(img.getWidth()*.40)) + img.getWidth()*.30),
							img.getHeight() - 1, (int) (Math.random() * 5)));
				}
				sector2.remove(currentSide);
				onSector1 = true;
			}
		}

		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);
			currentPoint.setColorR((int) (Math.random()*256));
			currentPoint.setColorG((int) (Math.random()*256));
			currentPoint.setColorB((int) (Math.random()*256));
			currentPoint.setColorAlpha((currentPoint.getStrength() + 1) * 51);
		}

		printOriginPoints(originPoints);

		return originPoints;
	}

	private int getBiggerSide(){
		int biggerSide;
		if (img.getHeight() > img.getWidth()) {
			biggerSide = img.getHeight();
		} else {
			biggerSide = img.getWidth();
		}
		return biggerSide;
	}
}
