package main.java.com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneFormatterTest {

    @Test
    public void testRussianNumberConversion() {
        String input = "+7 (999) 000-11-22";
        String expected = "+1 (999) 000-11-22";
        String actual = PhoneFormatter.formatPhoneNumber(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberWithDifferentSeparators() {
        String input = "8-912-345-67-89";
        String expected = "+1 (912) 345-67-89";
        String actual = PhoneFormatter.formatPhoneNumber(input);
        assertEquals(expected, actual);
    }
}