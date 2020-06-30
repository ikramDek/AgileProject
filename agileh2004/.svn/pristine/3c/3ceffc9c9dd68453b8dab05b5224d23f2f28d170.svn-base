import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.Layout;
import application.model.MyCircle;
import application.model.MyLine;
import application.model.MyRectangle;
import application.model.MyShape;

class LayoutTest {

	private Layout layout;

	private ArrayList<MyShape> shapesList;

	private String layoutName;

	private boolean isDisplayed;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("TEST Layout STARTED");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("TEST Layout STOPED");

	}

	@BeforeEach
	void setUp() throws Exception {

		layoutName = "layouttest";

		isDisplayed = false;

		layout = new Layout(layoutName, isDisplayed);

		shapesList = new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		layout=null;
		shapesList=null;
	
		assertNull(layout);
		assertNull(shapesList);
		
	}

	@Test
	void layoutTest() {

		int val = shapesList.size();

		MyShape shape1 = new MyRectangle();
		MyShape shape2 = new MyCircle();
		MyShape shape3 = new MyLine();

		Layout l1 = new Layout(layoutName, isDisplayed);

		layout.addShape(shape1);

		assertNotEquals(layout.getShapesList().size(), val);
		assertEquals(layout.getShapesList().size(), val + 1);

		layout.addShape(shape2);

		assertEquals(2, layout.getShapesList().size());

		assertEquals(layout.getLastShape(), shape2);

		assertEquals(layout.getShape(0), shape1);

		layout.removeShape(1);

		assertEquals(1, layout.getShapesList().size());

		layout.addShape(1, shape2);

		assertEquals(2, layout.getShapesList().size());

		assertEquals("layouttest", layout.getLayoutName());

		layout.setLayoutName(layoutName + "1");

		assertEquals("layouttest1", layout.getLayoutName());

		layout.setShapesList(shapesList);

		shapesList = new ArrayList<MyShape>();

		assertEquals(0, layout.getShapesList().size());

		layout.addShape(0, shape1);

		assertEquals(false, layout.isDisplayed());

		layout.setDisplayed(true);

		assertEquals(true, layout.isDisplayed());

		layout.setShape(0, new MyLine());

	//	l1 = layout.getClone();

	//	 assertEquals(l1.getLastShape().getClass(), layout.getLastShape().getClass());

	} 

}
