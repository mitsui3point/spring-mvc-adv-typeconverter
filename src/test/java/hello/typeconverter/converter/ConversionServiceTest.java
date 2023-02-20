package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    /**
     * conversionService test
     *
     * <h2>
     *     인터페이스 분리 원칙 - ISP(Interface Segregation Principle)<br>
     *      인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다.
     * </h2>
     * <div>
     *  {@link DefaultConversionService} 는 다음 두 인터페이스를 구현했다.
     *  {@link ConversionService} : 컨버터 사용에 초점
     *  {@link ConverterRegistry} : 컨버터 등록에 초점
     * </div>
     *
     * <div>
     * 이렇게 인터페이스를 분리하면
     * 컨버터를 사용하는 클라이언트와
     * 컨버터를 등록하고 관리하는 클라이언트의
     * 관심사를 명확하게 분리할 수 있다.
     * </div>
     *
     * <div>
     * 특히 컨버터를 사용하는 클라이언트는 ConversionService 만 의존하면 되므로,
     * 컨버터를 어떻게 등록하고 관리하는지는 전혀 몰라도 된다.
     * 결과적으로 컨버터를 사용하는 클라이언트는 꼭 필요한 메서드만 알게된다.
     * 이렇게 인터페이스를 분리하는 것을 ISP 라 한다.
     * </div>
     */
    @Test
    void conversionServiceTest() {
        //given
        DefaultConversionService conversionService = registeredUserCustomConverter();
        //when
        Integer actualStringToInteger = conversionService.convert("10", Integer.class);
        String actualIntegerToString = conversionService.convert(10, String.class);
        String actualIpPortToString = conversionService.convert(new IpPort("192.168.0.1", 8080), String.class);
        IpPort actualStringToIpPort = conversionService.convert("192.168.0.1:8080", IpPort.class);
        //then
        Arrays.stream(new Object[]{
                        actualStringToInteger,
                        actualIntegerToString,
                        actualIpPortToString,
                        actualStringToIpPort
        })
                .forEach(System.out::println);
        assertThat(actualStringToInteger).isEqualTo(10);
        assertThat(actualIntegerToString).isEqualTo("10");
        assertThat(actualIpPortToString).isEqualTo("192.168.0.1:8080");
        assertThat(actualStringToIpPort).isEqualTo(new IpPort("192.168.0.1", 8080));
    }

    private DefaultConversionService registeredUserCustomConverter() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        return conversionService;
    }
}