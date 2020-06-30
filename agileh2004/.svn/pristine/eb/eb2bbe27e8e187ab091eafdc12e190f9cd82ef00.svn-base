import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyRectangle;
import application.model.MyShape;
import javafx.scene.paint.Color;


class ColorFillerTest {

	ColorFiller  colorfiller ;
	private Color color;
	private MyShape shape;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("TEST ColorFiller  STARTED");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("TEST ColorFiller  STOPED");

	}

	
	@BeforeEach
	void setUp() throws Exception {
		shape = new MyRectangle();		
		color = Color.BLUE;
		colorfiller = new ColorFiller(color, shape);
	}

	
	@AfterEach
	void tearDown() throws Exception {
		
		colorfiller =null;
	}

	@Test
	void test() {
		shape.setFiller(colorfiller);
		assertNotNull(shape);
	}

}
