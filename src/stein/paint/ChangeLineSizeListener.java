package stein.paint;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ChangeLineSizeListener implements MouseWheelListener {

	private int width;
	private Canvas canvas;
	private int notches = 0;

	public ChangeLineSizeListener(Canvas canvas) {
		this.canvas = canvas;
		width = canvas.getStrokeInt();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getUnitsToScroll();
		notches = notches * -1;
		if (width + notches > 0) {
			canvas.setStrokeInt(width + notches);
		} else {
			canvas.setStrokeInt(1);
		}
	}
}