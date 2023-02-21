package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

public class MyNumberFormatterTest {

    private MyNumberFormatter myNumberFormatter;

    @BeforeEach
    void setUp() {
        myNumberFormatter = new MyNumberFormatter();
    }

    @Test
    void instanceOfFormatterTest() {
        boolean actual = myNumberFormatter instanceof Formatter<Number>;
        assertThat(actual).isTrue();
    }

    @Test
    void parseTest() throws ParseException {
        Number expected = 1000L;

        Number actual = myNumberFormatter.parse("1,000", Locale.KOREA);//"1,000" -> 1000

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void parseExceptionTest() {
        assertThatExceptionOfType(ParseException.class).isThrownBy(() -> {
            myNumberFormatter.parse("kkk", Locale.KOREA);//"kkk" -> exception
        });
    }

    @Test
    void printTest() {
        Number number = 10000L;
        Locale korea = Locale.KOREA;
        String expected = "10,000";

        String actual = myNumberFormatter.print(number, korea);

        assertThat(actual).isEqualTo(expected);
    }
}
