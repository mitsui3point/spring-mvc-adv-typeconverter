package hello.typeconverter.formatter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {
    @Test
    void formattingConversionServiceTest() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        //포맷터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        //컨버터 동작
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String ipPortString = "127.0.0.1:8080";

        String ipPortToString = conversionService.convert(ipPort, String.class);
        String ipPortToStringExpected = ipPortString;
        assertThat(ipPortToString).isEqualTo(ipPortToStringExpected);

        IpPort stringToIpPort = conversionService.convert(ipPortString, IpPort.class);
        IpPort stringToIpPortExpected = ipPort;
        assertThat(stringToIpPort).isEqualTo(stringToIpPortExpected);

        //포맷터 동작
        String numberFormattingComma = conversionService.convert(1000, String.class);
        String numberFormattingCommaExpected = "1,000";
        assertThat(numberFormattingComma).isEqualTo(numberFormattingCommaExpected);

        Number stringFormattingNumber = conversionService.convert("1,000", Number.class);
        Number stringFormattingNumberExpected = 1000L;
        assertThat(stringFormattingNumber).isEqualTo(stringFormattingNumberExpected);
    }
}
