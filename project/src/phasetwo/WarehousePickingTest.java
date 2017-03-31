/**
 * 
 */
package phasetwo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Test for WarehousePicking class
 *
 */
public class WarehousePickingTest {

	ArrayList<Location> locationList = new ArrayList<Location>(); 
	WarehousePicking warehousePicking = new WarehousePicking();
	String genericSoftPath = new File("src/phasetwo/traversal_table.csv").getAbsolutePath();


	/**
	 * Test method for {@link phasetwo.WarehousePicking#warehousePickingreader(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testWarehousePickingreader() throws FileNotFoundException {
		warehousePicking.warehousePickingreader(genericSoftPath);
	}

	/**
	 * Test method for {@link phasetwo.WarehousePicking#pickRequest(java.util.Map)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testPickRequest() throws FileNotFoundException {
		ArrayList<String> fasciaList = new ArrayList<String>();
		fasciaList.add("37");
		fasciaList.add("38");
		Map<Integer, Order> newRequest = new HashMap<Integer, Order>();
		Translate tr = new Translate();
		String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();
		tr.readFromcsvfile(transtanblePath);
		Order newOrder = new Order("Blue", "SES", tr);
		newRequest.put(1, newOrder);
		assertEquals(warehousePicking.pickRequest(newRequest),fasciaList);
		}

	/**
	 * Test method for {@link phasetwo.WarehousePicking#optimize(java.util.ArrayList)}.
	 */
	@Test
	public void testOptimize() {
		ArrayList<String> skuS = new ArrayList<String>();
		skuS.add("A");
		Location aLocation = new Location("A","0","0","2","A");
		ArrayList<Location> locatioNs = new ArrayList<Location>();
		locatioNs.add(aLocation);
		ArrayList<Location> locationS = new ArrayList<Location>();
		locationS = warehousePicking.optimize(skuS);
		assertEquals(locationS.toString(),locatioNs.toString());
	}

}
