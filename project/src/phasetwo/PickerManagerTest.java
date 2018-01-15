package phasetwo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test for PickManager class
 *
 */
public class PickerManagerTest {

  /**
   * Test method for {@link phasetwo.PickerManager#getORaddPicker(java.lang.String)}.
   */
  @Test
  public void testGetORaddPicker() {
    Picker piCker = new Picker("Alice");
    PickerManager pickerManager = new PickerManager();
    piCker = pickerManager.getORaddPicker("Alice");
    assertEquals("Alice", piCker.getName());
    piCker = pickerManager.getORaddPicker("Bob");
    assertEquals("Bob", piCker.getName());
    piCker = pickerManager.getORaddPicker("Alice");
    assertEquals("Alice", piCker.getName());
  }

  /**
   * Test method for {@link phasetwo.PickerManager#getFreePicker()}.
   */
  @Test
  public void testGetFreePicker() {
    Picker aLice = new Picker("Alice");
    Picker boB = new Picker("boB");
    PickerManager pickerManager = new PickerManager();
    pickerManager.addFreePicker(aLice);
    pickerManager.addFreePicker(boB);
    ArrayList<Picker> freePicker = new ArrayList<Picker>();
    freePicker.add(aLice);
    freePicker.add(boB);
    assertEquals(freePicker, pickerManager.getFreePicker());
  }

  /**
   * Test method for {@link phasetwo.PickerManager#addFreePicker(phasetwo.Picker)}.
   */
  @Test
  public void testAddFreePicker() {
    Picker aLice = new Picker("Alice");
    PickerManager pickerManager = new PickerManager();
    pickerManager.addFreePicker(aLice);
    ArrayList<Picker> freePicker = new ArrayList<Picker>();
    freePicker.add(aLice);
    assertEquals(freePicker, pickerManager.getFreePicker());
  }

  /**
   * Test method for {@link phasetwo.PickerManager#deletFreePicker(phasetwo.Picker)}.
   */
  @Test
  public void testDeletFreePicker() {
    Picker aLice = new Picker("Alice");
    Picker boB = new Picker("boB");
    PickerManager pickerManager = new PickerManager();
    pickerManager.addFreePicker(aLice);
    pickerManager.addFreePicker(boB);
    ArrayList<Picker> freePicker = new ArrayList<Picker>();
    freePicker.add(aLice);
    pickerManager.deletFreePicker(boB);
    assertEquals(freePicker, pickerManager.getFreePicker());
  }

  /**
   * Test method for {@link phasetwo.PickerManager#deletPicker(phasetwo.Picker)}.
   */
  @Test
  public void testDeletPicker() {
    Picker piCker = new Picker("Alice");
    Picker boB = new Picker("Bob");
    PickerManager pickerManager = new PickerManager();
    piCker = pickerManager.getORaddPicker("Alice");
    boB = pickerManager.getORaddPicker("Bob");
    pickerManager.deletPicker(piCker);
    ArrayList<Picker> pickerList = new ArrayList<Picker>();
    pickerList.add(boB);
    assertEquals(pickerList, pickerManager.getPickerList());

  }

}
