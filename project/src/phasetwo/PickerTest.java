/**
 * 
 */
package phasetwo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Unit tests for Picker class.
 */
public class PickerTest {

	/**
	 * Test method for  {@link phasetwo.Picker#Picker(java.lang.String)}.
	 */
	@Test
	public void testPicker() {
		Picker aLice = new Picker("Alice");
		assertEquals("Alice",aLice.getName());
	}

	/**
	 * Test method for {@link phasetwo.Picker#addtoFolkLift(java.lang.String)}.
	 */
	@Test
	public void testAddtoFolkLift() {
		ArrayList<String> forkLift = new ArrayList<String>();
		forkLift.add("ABC");
		forkLift.add("abc");
		forkLift.add("123");
		Picker aLice = new Picker("Alice");
		aLice.addtoFolkLift("ABC");
		aLice.addtoFolkLift("abc");
		aLice.addtoFolkLift("123");
		assertEquals(forkLift,aLice.getForkLift());
	}

	/**
	 * Test method for {@link phasetwo.Picker#addLocation(java.util.ArrayList)}.
	 */
	@Test
	public void testAddLocation() {
		ArrayList<Location> locationlist = new ArrayList<Location>();
		Location locationOne = new Location("A","A","A","A","A");
		locationlist.add(locationOne);
		Picker aLice = new Picker("Alice");
		aLice.addLocation(locationlist);
		assertEquals(locationOne.toString(),aLice.getNextLocation());
	}

	/**
	 * Test method for {@link phasetwo.Picker#checkPickerScanedCorrectSKU(java.lang.String)}.
	 */
	@Test
	public void testCheckPickerScanedCorrectSKU() {
		Picker aLice = new Picker("Alice");
		ArrayList<Location> locationlist = new ArrayList<Location>();
		Location locationOne = new Location("A","A","A","A","20");
		Location locationTwo = new Location("A","A","A","A","5");
		locationlist.add(locationOne);
		locationlist.add(locationTwo);
		aLice.addLocation(locationlist);
		assertEquals(true,aLice.checkPickerScanedCorrectSKU("20"));
		Integer nextPick = 0;
		assertEquals(nextPick,aLice.getcurrentPickNum());
		assertEquals(false,aLice.checkPickerScanedCorrectSKU("2"));
	}

	/**
	 * Test method for {@link phasetwo.Picker#checkgotAllSKU()}.
	 */
	@Test
	public void testCheckgotAllSKU() {
		Picker aLice = new Picker("Alice");
		assertEquals(false,aLice.checkgotAllSKU());
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		aLice.addtoFolkLift("a");
		assertEquals(true,aLice.checkgotAllSKU());
		
	}

	/**
	 * Test method for {@link phasetwo.Picker#getForkLift()}.
	 */
	@Test
	public void testGetForkLift() {
		Picker aLice = new Picker("Alice");
		assertEquals(false,aLice.checkgotAllSKU());
		aLice.addtoFolkLift("a");
		ArrayList<String> forkLift = new ArrayList<String>();
		forkLift.add("a");
		assertEquals(forkLift,aLice.getForkLift());
	}

	/**
	 * Test method for {@link phasetwo.Picker#getRequestid()}.
	 */
	@Test
	public void testGetRequestid() {
		Picker aLice = new Picker("Alice");
		Integer requestID = 1;
		aLice.setRequestid(requestID);
		assertEquals(requestID,aLice.getRequestid());
	}

	/**
	 * Test method for {@link phasetwo.Picker#getName()}.
	 */
	@Test
	public void testGetName() {
		Picker aLice = new Picker("Alice");
		assertEquals("Alice",aLice.getName());
	}

	/**
	 * Test method for {@link phasetwo.Picker#getNextLocation()}.
	 */
	@Test
	public void testGetNextLocation() {
		Picker aLice = new Picker("Alice");
		ArrayList<Location> locationlist = new ArrayList<Location>();
		Location locationOne = new Location("A","A","A","A","20");
		Location locationTwo = new Location("A","A","A","A","5");
		locationlist.add(locationOne);
		locationlist.add(locationTwo);
		aLice.addLocation(locationlist);
		assertEquals(locationOne.toString(),aLice.getNextLocation());
		assertEquals(locationlist,aLice.getLocationList());
	}

	/**
	 * Test method for {@link phasetwo.Picker#setRequestid(int)}.
	 */
	@Test
	public void testSetRequestid() {
		Picker aLice = new Picker("Alice");
		Integer iD = 20;
		aLice.setRequestid(iD);
		assertEquals(iD,aLice.getRequestid());
	}

}
