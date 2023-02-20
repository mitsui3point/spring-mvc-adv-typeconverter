package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

public class IpPortToStringConverterTest {
    private Converter converter;

    @BeforeEach
    void setUp() {
        converter = new IpPortToStringConverter();
    }

    @Test
    void ipPortToStringConvertTest() {
        Object actual = converter.convert(new IpPort("192.168.0.1", 8080));
        Assertions.assertThat(actual).isEqualTo("192.168.0.1:8080");
    }
}
