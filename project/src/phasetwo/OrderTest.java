package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	Translate tr = new Translate();
	public static String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();

	@Before
	public void setUp() throws FileNotFoundException {
		tr.readFromcsvfile(transtanblePath);
	}

	@Test
	public void testOrder() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getfrontStatus(), "ordered");
	}

	@Test
	public void testSetFrontStatus() {
		Order order = new Order("Blue", "SES", tr);
		order.setfrontStatus("picked");
		assertEquals(order.getfrontStatus(), "picked");
		assertEquals(order.getbackStatus(), "ordered");
	}

	@Test
	public void testSetBackStatus() {
		Order order = new Order("Blue", "SES", tr);
		order.setbackStatus("picked");
		assertEquals(order.getfrontStatus(), "ordered");
		assertEquals(order.getbackStatus(), "picked");
	}

	@Test
	public void testGetOrderCount() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getOrderid(), 1);

	}

	@Test
	public void testResetStatus() {
		Order order = new Order("Blue", "SES", tr);
		order.setbackStatus("picked");
		order.resetStatus();
		assertEquals(order.getbackStatus(), "ordered");

	}

	@Test
	public void testGetFront() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getFront(), "37");
	}

	@Test
	public void testNotinSystem() {
		Order order = new Order("Blue", "W", tr);
		assertEquals(order.getFront(),null);
	}

	@Test
	public void testGetBack() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getBack(), "38");
	}

	@Test
	public void testGetColor() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getColour(), "Blue");
	}

	@Test
	public void testGetModel() {
		Order order = new Order("Blue", "SES", tr);
		assertEquals(order.getModel(), "SES");
	}

	@Test
	public void testEqual() {
		Order other = new Order("Blue", "SES", tr);
		Order order = new Order("Blue", "SES", tr);
		assertTrue(order.equals(other));
	}

}
