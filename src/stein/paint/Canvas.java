package stein.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ConnectException;

import javax.swing.JButton;
import javax.swing.JComponent;

import stein.paint.message.LoopbackNetworkModule;
import stein.paint.message.NetworkModule;
import stein.paint.message.OnlineNetworkModule;

public class Canvas extends JComponent {

	private BufferedImage img;
	private Graphics2D g2;
	private Color color = Color.BLACK;
	private int strokeInt = 5;
	private Stroke stroke = new BasicStroke(strokeInt);
	private DrawListener listener;
	private boolean clear;
	private NetworkModule module;

	// JButton button;

	public Canvas() {
		try {
			PaintClient client = new PaintClient(this);
			module = new OnlineNetworkModule(client);
		} catch (ConnectException e) {
			module = new LoopbackNetworkModule(this);
		} catch (IOException d) {
			d.printStackTrace();
		}
		img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		listener = new PencilListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		// button = new JButton();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		if (clear == false) {
			listener.drawPreview((Graphics2D) g);
		} else {
			clear = false;
		}

	}

	public void clearCanvas() {
		img = null;
		img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		clear = true;
		this.repaint();

	}

	public void setDrawListenerToRectangle() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new RectangleListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void setDrawListenerToBucketFill() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new BucketFillListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);

	}

	public void setDrawListenerToPencil() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new PencilListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void setDrawListenerToFillRectangle() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new FillRectangleListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void setDrawListenerToOval() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new FillOvalListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	public void setDrawListenerToFillOVal() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new OvalListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	public void setDrawListenerToLine() {
		this.removeMouseListener(listener);
		this.removeMouseMotionListener(listener);
		listener = new LineListener(this);
		addMouseListener(listener);
		addMouseMotionListener(listener);

	}

	public NetworkModule getModule() {
		return module;
	}

	public void setModule(NetworkModule module) {
		this.module = module;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setStrokeInt(int strokeInt) {
		stroke = new BasicStroke(strokeInt);
	}

	public int getStrokeInt() {
		return strokeInt;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}


}
