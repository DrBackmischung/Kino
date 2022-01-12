package de.wi2020sebgroup1.cinema.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CinemaRoomTest {
	
	@Test
	@DisplayName("Test the 1st constructor")
    public void testConstructor() {
		Cinema c = new Cinema(null, null, null, 0, 0, null);
		CinemaRoomSeatingPlan cr = new CinemaRoomSeatingPlan(10);
		CinemaRoom o = new CinemaRoom(1, true, c, "testRoom");
		o.setCinemaRoomSeatingPlan(cr);
		assertEquals(o.getStory(), 1);
		assertEquals(o.isWheelchairAccessible(), true);
		assertEquals(o.getCinema(), c);
		assertEquals(o.getRoomName(), "testRoom");
    }
	
	@Test
	@DisplayName("Test the 2nd constructor")
    public void test2ndConstructor() {
		CinemaRoom o = new CinemaRoom(1, true, "testRoom");
		assertEquals(o.getStory(), 1);
		assertEquals(o.isWheelchairAccessible(), true);
		assertEquals(o.getRoomName(), "testRoom");
    }
	
	@Test
	@DisplayName("Test Getter/Setter")
    public void testGetterSetter() {
		Cinema c = new Cinema(null, null, null, 0, 0, null);
		CinemaRoom o = new CinemaRoom(1, true, c, "testRoom");
		o.setStory(2);
		assertEquals(o.getStory(), 2);
		o.setWheelchairAccessible(false);
		assertEquals(o.isWheelchairAccessible(), false);
		o.setCinema(null);
		assertEquals(o.getCinema(), null);
    }
	
	@Test
	@DisplayName("Equals consistency")
    public void testCompare() {
		UUID u = new UUID(2,2);
		CinemaRoomSeatingPlan cr = new CinemaRoomSeatingPlan(10);
		CinemaRoom o = new CinemaRoom(1, false, null, null);
		o.setId(u);
		o.setSeatingPlan(cr);
		CinemaRoom o2 = new CinemaRoom(1, false, null, null);
		o2.setId(u);
		o2.setSeatingPlan(cr);
		assertEquals(o.hashCode(), o2.hashCode());
		assertEquals(o.equals(o2), true);
		CinemaRoom o3 = new CinemaRoom(1, false, null, null);
		CinemaRoom o4 = new CinemaRoom(1, false, null, null);
		assertEquals(o3.hashCode(), o4.hashCode());
		assertEquals(o3.equals(o4), true);
    }
    
    
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	@DisplayName("Equals inconsistency fail")
    public void testCompareFail() {
		CinemaRoom o = new CinemaRoom(1, true, "testRoom");
		CinemaRoom o2 = new CinemaRoom(2, true, "testRoom");
		CinemaRoom o3 = new CinemaRoom(1, false, "testRoom");
		CinemaRoom o4 = new CinemaRoom(1, true, "testRoom2");
		CinemaRoom o5 = new CinemaRoom(1, true, "testRoom");
		o5.setCinema(new Cinema());
		CinemaRoom o6 = new CinemaRoom(1, true, "testRoom");
		o6.setCinemaRoomSeatingPlan(new CinemaRoomSeatingPlan());
		CinemaRoom o7 = null;
		String s = "Test";
		assertEquals(o.equals(o2), false);
		assertEquals(o.equals(o3), false);
		assertEquals(o.equals(o4), false);
		assertEquals(o.equals(o5), false);
		assertEquals(o.equals(o6), false);
		assertEquals(o.equals(o7), false);
		assertEquals(o.equals(s), false);
		CinemaRoom onull = new CinemaRoom(1, true, "testRoom");
		onull.setId(new UUID(2,2));
		assertEquals(o.equals(onull), false);
    }
    
}
