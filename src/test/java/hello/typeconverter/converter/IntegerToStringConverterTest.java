package hello.typeconverter.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerToStringConverterTest {
    private Converter converter;

    @BeforeEach
    void setUp() {
        converter = new IntegerToStringConverter();
    }

    @Test
    @DisplayName("컨버터 Integer to String 반환값 테스트")
    void integerToStringTest() {
        Object convert = converter.convert(20);
        assertThat(convert).isEqualTo("20");
    }
}
