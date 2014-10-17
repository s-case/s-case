package storyboards.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleShape extends Ellipse {

	/**
	 * Fills the ellipse.
	 * 
	 * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
	 */
	protected void fillShape(Graphics graphics) {
		graphics.fillOval(getSmallerBounds());
	}

	/**
	 * Outlines the ellipse.
	 * 
	 * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
	 */
	protected void outlineShape(Graphics graphics) {
		setLineWidth(2);
		setForegroundColor(ColorConstants.black);
		graphics.drawOval(getOptimizedBounds());
	}

	private Rectangle getOptimizedBounds() {
		float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
		int inset1 = (int) Math.floor(lineInset);
		int inset2 = (int) Math.ceil(lineInset);

		Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
		r.x += inset1;
		r.y += inset1;
		r.width -= inset1 + inset2;
		r.height -= inset1 + inset2;
		return r;
	}

	private Rectangle getSmallerBounds() {
		float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
		int inset1 = (int) Math.floor(lineInset);
		int inset2 = (int) Math.ceil(lineInset);

		Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
		r.x += inset1;
		r.y += inset1;
		r.width -= inset1 + inset2;
		r.height -= inset1 + inset2;

		int stepx = r.width / 10;
		int stepy = r.height / 10;
		r.x += 3 * stepx;
		r.y += 3 * stepy;
		r.width -= 6 * stepx;
		r.height -= 6 * stepy;
		return r;
	}
}