package main.java.com.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class PhoneFormatterTest {
    @Test
    public void testFormatPhoneNumber() {
        String rawNumber = "+7 (999) 000-11-22";
        // Вызов нашего метода из основного класса
        String formatted = PhoneFormatter.formatPhoneNumber(rawNumber);
        // Проверка, что метод отработал как ожидается
        Assertions.assertEquals("+1 (999) 000-11-22", formatted);
    }
}