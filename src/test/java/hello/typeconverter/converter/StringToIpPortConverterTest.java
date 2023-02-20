package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

public class StringToIpPortConverterTest {
    private Converter converter;

    @BeforeEach
    void setUp() {
        converter = new StringToIpPortConverter();
    }

    @Test
    void stringToIpPortConvertTest() {
        Object actual = converter.convert("192.168.0.1:8080");
        Assertions.assertThat(actual).isEqualTo(new IpPort("192.168.0.1", 8080));
    }
}
