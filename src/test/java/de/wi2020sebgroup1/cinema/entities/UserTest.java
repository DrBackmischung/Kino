package de.wi2020sebgroup1.cinema.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	@DisplayName("Test the 1st constructor")
    public void testConstructor() {
		Role r = new Role(null, null);
		User o = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, null, null, null, null, null);
		assertEquals(o.getUserName(), "DrBackmischung");
        assertEquals(o.getName(), "Neunzig");
        assertEquals(o.getFirstName(), "Mathis");
        assertEquals(o.getEmail(), "mathis.neunzig@gmail.com");
        assertEquals(o.getPassword(), "ichBinDumm");
        assertEquals(o.getRole(), r);
	}
	
	@Test
	@DisplayName("Test the 2nd constructor")
    public void testConstructor2() {
		User o = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", null, null, null, null, null);
		assertEquals(o.getUserName(), "DrBackmischung");
        assertEquals(o.getName(), "Neunzig");
        assertEquals(o.getFirstName(), "Mathis");
        assertEquals(o.getEmail(), "mathis.neunzig@gmail.com");
        assertEquals(o.getPassword(), "ichBinDumm");
        assertEquals(o.getRole().getDescription(), "Default User");
        assertEquals(o.getRole().getAutorization(), "user");
	}
	
	@Test
	@DisplayName("Test Getter/Setter")
    public void testGetterSetter() {
		Role r = new Role(null, null);
		User o = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, null, null, null, null, null);
		o.setUserName("User");
		assertEquals(o.getUserName(), "User");
		o.setName("name");
        assertEquals(o.getName(), "name");
        o.setFirstName("FN");
        assertEquals(o.getFirstName(), "FN");
        o.setEmail("mail@gmail.com");
        assertEquals(o.getEmail(), "mail@gmail.com");
        o.setPassword("pw");
        assertEquals(o.getPassword(), "pw");
        o.setRole(null);
        assertEquals(o.getRole(), null);
	}
	
	@Test
	@DisplayName("Equals consistency")
    public void testCompare() {
		UUID u = new UUID(2,2);
		Role r = new Role(null, null);
		City c = new City();
		CreditCard cc = new CreditCard();
		User o = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		o.setId(u);
		User o2 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		o2.setId(u);
		assertEquals(o.hashCode(), o2.hashCode());
		assertEquals(o.equals(o2), true);
		User o3 = new User(null, null, null, null, null, null, null, null, null, null, null);
		User o4 = new User(null, null, null, null, null, null, null, null, null, null, null);
		assertEquals(o3.hashCode(), o4.hashCode());
		assertEquals(o3.equals(o4), true);
    }
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	@DisplayName("Equals inconsistency fail")
    public void testCompareFail() {
		Role r = new Role(null, null);
		City c = new City();
		CreditCard cc = new CreditCard();
		User o = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		User o2 = new User("DrPudding", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		User o3 = new User("DrBackmischung", "Achzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		User o4 = new User("DrBackmischung", "Neunzig", "Neele", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		User o5 = new User("DrBackmischung", "Neunzig", "Mathis", "kitty.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		User o6 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinSchlau", r, "strasse", "21", c, "mail", cc);
		User o7 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", null, "strasse", "21", c, "mail", cc);
		User o8 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse2", "21", c, "mail", cc);
		User o9 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "212", c, "mail", cc);
		User o10 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", null, "mail", cc);
		User o11 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail2", cc);
		User o12 = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", null);
		User o13 = null;
		String s = "Test";
		assertEquals(o.equals(o2), false);
		assertEquals(o.equals(o3), false);
		assertEquals(o.equals(o4), false);
		assertEquals(o.equals(o5), false);
		assertEquals(o.equals(o6), false);
		assertEquals(o.equals(o7), false);
		assertEquals(o.equals(o8), false);
		assertEquals(o.equals(o9), false);
		assertEquals(o.equals(o10), false);
		assertEquals(o.equals(o11), false);
		assertEquals(o.equals(o12), false);
		assertEquals(o.equals(o13), false);
		assertEquals(o.equals(s), false);
		User onull = new User("DrBackmischung", "Neunzig", "Mathis", "mathis.neunzig@gmail.com", "ichBinDumm", r, "strasse", "21", c, "mail", cc);
		onull.setId(new UUID(2,2));
		assertEquals(o.equals(onull), false);
    }
	
}
