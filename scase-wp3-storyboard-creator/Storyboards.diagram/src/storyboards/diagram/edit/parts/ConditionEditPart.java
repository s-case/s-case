package storyboards.diagram.edit.parts;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.draw2d.CenterLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

import storyboards.diagram.edit.policies.ConditionItemSemanticEditPolicy;
import storyboards.diagram.part.StoryboardsVisualIDRegistry;

/**
 * @generated
 */
public class ConditionEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2010;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ConditionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ConditionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

			protected Command createAddCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command createMoveChildCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new ConditionFigure();
	}

	/**
	 * @generated
	 */
	public ConditionFigure getPrimaryShape() {
		return (ConditionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ConditionNameEditPart) {
			((ConditionNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureConditionNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ConditionNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40) {
			public PointList getPolygonPoints() {
				PointList points = new PointList(4);
				Rectangle anchRect = getHandleBounds();
				int lx = anchRect.x;
				int ly = anchRect.y;
				int lwidth = anchRect.width / 10;
				int lheight = anchRect.height / 5;
				points.addPoint(lx + lwidth * 2, ly); // A1
				points.addPoint(lx + lwidth * 10, ly); // A2
				points.addPoint(lx + lwidth * 8, ly + lheight * 5); // A3
				points.addPoint(lx, ly + lheight * 5); // A4
				points.addPoint(lx + lwidth * 2, ly); // A1
				return points;
			}
		};
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * 
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(StoryboardsVisualIDRegistry.getType(ConditionNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class ConditionFigure extends ScalablePolygonShape {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureConditionNameFigure;

		/**
		 * @generated NOT
		 */
		public ConditionFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(true);

			this.setLayoutManager(layoutThis);

			this.addPoint(new Point(getMapMode().DPtoLP(8), getMapMode().DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(40), getMapMode().DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(32), getMapMode().DPtoLP(40)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode().DPtoLP(40)));
			this.setFill(true);
			this.setBackgroundColor(ColorConstants.lightGray);
			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(80), getMapMode().DPtoLP(40)));
			createContents();

			this.setLayoutManager(new StackLayout() {
				public void layout(IFigure figure) {
					Rectangle r = figure.getClientArea();
					List children = figure.getChildren();

					IFigure child;
					Dimension d;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						d = child.getPreferredSize(r.width, r.height);
						d.width = Math.min(d.width, r.width);
						d.height = Math.min(d.height, r.height);
						Rectangle childRect = new Rectangle(r.x + (r.width - d.width) / 2, r.y + (r.height - d.height)
								/ 2, d.width, d.height);
						child.setBounds(childRect);
					}
				}
			});
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			fFigureConditionNameFigure = new WrappingLabel();

			fFigureConditionNameFigure.setText("<...>");

			fFigureConditionNameFigure.setAlignment(PositionConstants.CENTER);

			this.add(fFigureConditionNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureConditionNameFigure() {
			return fFigureConditionNameFigure;
		}

		/**
		 * @generated NOT
		 */
		protected void fillShape(Graphics graphics) {
			float scale = (float) graphics.getAbsoluteScale();
			Rectangle r = getBounds().getCopy();
			Point topLeft = r.getTopLeft();
			Point bottomRight = r.getBottomRight();
			Pattern pattern = new Pattern(Display.getCurrent(), topLeft.x * scale, topLeft.y * scale, bottomRight.x
					* scale, bottomRight.y * scale, ColorConstants.lightGray, ColorConstants.white);
			graphics.setBackgroundPattern(pattern);

			PointList points = new PointList(4);
			Rectangle anchRect = getBounds();
			int lx = anchRect.x;
			int ly = anchRect.y;
			int lwidth = anchRect.width / 10;
			int lheight = anchRect.height / 5;
			points.addPoint(lx + lwidth * 2, ly); // A1
			points.addPoint(lx + lwidth * 10, ly); // A2
			points.addPoint(lx + lwidth * 8, ly + lheight * 5); // A3
			points.addPoint(lx, ly + lheight * 5); // A4
			points.addPoint(lx + lwidth * 2, ly); // A1
			graphics.fillPolygon(points);

			graphics.setBackgroundPattern(null);
			pattern.dispose();
		}
	}

}
