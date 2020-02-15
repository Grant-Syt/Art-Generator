package art_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArtGeneratorEditor {

	public static void main(String[] args) {
		
		JFrame mainFrame = new JFrame("Main Frame");
		mainFrame.setSize(1920,1080);
		mainFrame.setMinimumSize(new Dimension(1200, 600));
		mainFrame.setLocation(0,0);
		mainFrame.setBackground(Color.lightGray);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArtGeneratorImpl artGenerator = new ArtGeneratorImpl(720, 480);
		
		JPanel imgFrame = new JPanel();
		Dimension imgFrameDimension = new Dimension(720, 480);
		imgFrame.setSize(imgFrameDimension);
		imgFrame.setMinimumSize(new Dimension(imgFrameDimension));
		mainFrame.getContentPane().add(BorderLayout.CENTER, imgFrame);
		ImageIcon imgIcon = new ImageIcon(artGenerator.getImage());
		JLabel imgLabel = new JLabel(imgIcon);
		imgFrame.add(imgLabel);
		
		JPanel lButtonFrame = new JPanel();
		Dimension lButtonFrameDimension = new Dimension(50, 1080);
		lButtonFrame.setSize(lButtonFrameDimension);
		lButtonFrame.setMinimumSize(new Dimension(lButtonFrameDimension));
		lButtonFrame.setLayout(new BoxLayout (lButtonFrame, BoxLayout.Y_AXIS));
		mainFrame.getContentPane().add(BorderLayout.WEST, lButtonFrame);
		final JButton button1 = new JButton("Draw Light Gradient");
		lButtonFrame.add(button1);
		final JButton button2 = new JButton("Draw Dark Gradient");
		lButtonFrame.add(button2);
		final JButton button3 = new JButton("Draw Box Art");
		lButtonFrame.add(button3);
		final JButton button4 = new JButton("Draw Cicle Art");
		lButtonFrame.add(button4);
		final JButton button5 = new JButton("Save");
		lButtonFrame.add(button5);
//		final JButton button11 = new JButton("New Image");
//		lButtonFrame.add(button11);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.drawLightGradient();
				imgLabel.repaint();
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.drawDarkGradient();
				imgLabel.repaint();
			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.drawBoxArt();
				imgLabel.repaint();
			}
		});
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.drawCircleArt();
				imgLabel.repaint();
			}
		});
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.saveCurrentImage("test");
				imgLabel.repaint();
			}
		});
//		button11.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				artGenerator.newImage(1920, 1080);
//				imgIcon.setImage(artGenerator.getImage());
//				imgFrameDimension.width = 1080;
//				imgFrameDimension.height = 1920;
//				imgFrame.setSize(imgFrameDimension);
//				imgFrame.setMinimumSize(new Dimension(imgFrameDimension));
//				imgLabel.repaint();
//				imgFrame.repaint();
//			}
//		});

		JPanel rButtonFrame = new JPanel();
		Dimension rButtonFrameDimension = new Dimension(50, 1080);
		rButtonFrame.setSize(rButtonFrameDimension);
		rButtonFrame.setMinimumSize(new Dimension(rButtonFrameDimension));
		rButtonFrame.setLayout(new BoxLayout (rButtonFrame, BoxLayout.Y_AXIS));
		mainFrame.getContentPane().add(BorderLayout.EAST, rButtonFrame);
		final JButton button6 = new JButton("Click Me");
		rButtonFrame.add(button6);
		final JButton button7 = new JButton("Click Me");
		rButtonFrame.add(button7);
		final JButton button8 = new JButton("Click Me");
		rButtonFrame.add(button8);
		final JButton button9 = new JButton("Click Me");
		rButtonFrame.add(button9);
		final JButton button10 = new JButton("Click Me");
		rButtonFrame.add(button10);
//		button6.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				artGenerator.drawLightGradient();
//				imgFrame.add(new JLabel(new ImageIcon(artGenerator.getImage())));
//
//			}
//		});
		
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

}
