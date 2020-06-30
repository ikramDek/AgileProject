package application.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ComposedShape implements MyShape {
	private ArrayList<MyShape> shapesList;
	private ComposedShape root;
	private String layoutName;
	protected Circle[] circles;
	private String shapeName;
	private static final double HANDELS_SIZE_CONST = 4;
	
	public ComposedShape() {
		shapesList = new ArrayList<>();
		initCircles();
		root = null;
	}

	public Circle[] getCircles() {
		return this.circles;
	}
	
	public void initCircles() {
		this.circles = new Circle[4];
		for (int i = 0; i < circles.length; i++) {
			circles[i] = new Circle(HANDELS_SIZE_CONST, Color.GOLD);
		}
	}

	public void setHadelsVisible(Boolean value) {
		for (Circle circle : circles) {
			circle.setVisible(value);
			
		}
	}

	public void setHandelsPos() {
		initCircles();
		this.circles[0].setCenterX(this.getStartX());
		this.circles[0].setCenterY(this.getStartY());

		this.circles[1].setCenterX(this.getEndX());
		this.circles[1].setCenterY(this.getStartY());
		
		this.circles[2].setCenterX(this.getStartX());
		this.circles[2].setCenterY(this.getEndY());

		this.circles[3].setCenterX(this.getEndX());
		this.circles[3].setCenterY(this.getEndY());
		

	}
	
	@Override
	public double getStartX() {
		double min = Double.MAX_VALUE;
		for (MyShape myShape : shapesList) {
			min = Math.min(min, myShape.getStartX());
		}
		return min;
	}

	@Override
	public double getStartY() {
		double min = Double.MAX_VALUE;
		for (MyShape myShape : shapesList) {
			min = Math.min(min, myShape.getStartY());
		}
		return min;

	}

	@Override
	public double getEndX() {
		double max = 0;
		for (MyShape myShape : shapesList) {
			max = Math.max(max, myShape.getEndX());
		}
		return max;

	}

	@Override
	public double getEndY() {
		double max = 0;
		for (MyShape myShape : shapesList) {
			max = Math.max(max, myShape.getEndY());
		}
		return max;
	}

	@Override
	public void setSelected(boolean b) {
		for (MyShape myShape : shapesList) {
			myShape.setSelected(b);
		}
	}

	@Override
	public void disableDraggable() {
		for (MyShape myShape : shapesList) {
			myShape.disableDraggable();
		}

	}

	@Override
	public boolean isSelected() {
		for (MyShape myShape : shapesList) {
			if (!myShape.isSelected()) {
				return false;
			}

		}
		return true;

	}

	@Override
	public void setFiller(Filler filler) {
		for (MyShape myShape : shapesList) {
			myShape.setFiller(filler);
		}

	}

	@Override
	public void setPattern(boolean b) {
		for (MyShape myShape : shapesList) {
			myShape.setPattern(b);
		}
	}

	@Override
	public void updateFields() {
		for (MyShape myShape : shapesList) {
			myShape.updateFields();
		}

	}

	@Override
	public void draw(ObservableList<Node> children) {
		for (MyShape shape : shapesList) {
			shape.draw(children);
		}
	}

	@Override
	public void changeStrokeColor(Color value) {
		for (MyShape myShape : shapesList) {
			myShape.changeStrokeColor(value);
		}
	}

	@Override
	public void setFill(Paint pattern) {
		for (MyShape myShape : shapesList) {
			myShape.setFill(pattern);
		}
	}

	@Override
	public void rotateShape() {
		for (MyShape myShape : shapesList) {
			myShape.rotateShape();
		}
	}

	@Override
	public void moveShape(int noStep, int i) {
		for (MyShape myShape : shapesList) {
			myShape.moveShape(noStep, i);
		}
	}

	@Override
	public void enlarge() {
		for (MyShape myShape : shapesList) {
			myShape.enlarge();
		}
	}

	@Override
	public void changeStrokeWidth(int val) {
		for (MyShape myShape : shapesList) {
			myShape.changeStrokeWidth(val);
		}
	}

	public void setList(List<MyShape> selectedList) {
		shapesList = (ArrayList<MyShape>) selectedList;
	}

	public ArrayList<SingleShape> getSingleShapeList() {
		ArrayList<SingleShape> list = new ArrayList<>();
		for (MyShape myShape : shapesList) {
			list.addAll(myShape.getSingleShapeList());
		}
		return list;
	}

	@Override
	public void setRoot(ComposedShape compShape) {
		root = compShape;
	}

	@Override
	public ComposedShape getRoot() {
		return root;
	}

	@Override
	public boolean isInside(Double startX, Double startY, Double currentX, Double currentY) {
		for (MyShape myShape : shapesList) {
			if (myShape.isInside(startX, startX, currentX, currentY))
				return true;
		}
		return false;

	}

	@Override
	public MyShape getClone() {
		ArrayList<MyShape> list = new ArrayList<>();
		for (MyShape myShape : shapesList) {
			list.add(myShape.getClone());
		}
		ComposedShape shape = new ComposedShape();
		shape.setList(list);
		return shape;
	}

	@Override
	public String getLayout() {
		return layoutName;
	}

	@Override
	public ArrayList<MyShape> getList() {
		return shapesList;
	}

	@Override
	public Double getWidth() {
		return 0d;
	}

	@Override
	public Double getHeight() {
		return 0d;
	}

	@Override
	public Element createNode(Element sh, Document dom) {
		for (MyShape myShape : getList()) {
			if (myShape.getList() == null) {
				sh = myShape.createNode(sh, dom);
			} else {
				Element ch = dom.createElement(myShape.getClass().getSimpleName());
				for (MyShape currentShape : myShape.getList()) {
					Element dh = dom.createElement(currentShape.getClass().getSimpleName());
					dh = currentShape.createNode(dh, dom);
					ch.appendChild(dh);
				}
				sh.appendChild(ch);
			}
		}
		return sh;
	}

	public void setLayout(String layout) {
		layoutName = layout;
	}

	@Override
	public TreeItem<String> getTreeItem() {
		TreeItem<String> tmpTreeItem = new TreeItem<>(this.toString());
		if(shapeName == null)
			for (MyShape childrenShape : getList()) {
				tmpTreeItem.getChildren().add(childrenShape.getTreeItem());
			}
		
		tmpTreeItem.setExpanded(true);
		return tmpTreeItem;
	}

	@Override
	public String toString() {
		if(shapeName == null)
			return "Group of " + shapesList.size() + " shapes";
		else
			return shapeName;
	}

	@Override
	public void flipShape(boolean value) {
		
		this.setHandelsPos();
		this.setHadelsVisible(true);
		double xpivot = (this.getEndX() - this.getStartX()) / 2 + this.getStartX();
		double ypivot = (this.getEndY() - this.getStartY()) / 2 + this.getStartY();

		for (MyShape myShape : shapesList) {
			
			myShape.flipComposedShape(value, xpivot, ypivot);
			myShape.updateFields();
		}

	}

	@Override
	public void flipComposedShape(boolean value, double xPivot, double yPivot) {

	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		this.shapeName = string;
	}

	

}