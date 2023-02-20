package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.*;

public class StringToIntegerConverterTest {

    private Converter converter;

    @BeforeEach
    void setUp() {
        converter = new StringToIntegerConverter();
    }

    @Test
    @DisplayName("컨버터 String to Integer 반환값 테스트")
    void stringToIntegerTest() {
        Object convert = converter.convert("20");
        assertThat(convert).isEqualTo(20);
    }
}
