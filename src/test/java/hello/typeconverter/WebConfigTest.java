package hello.typeconverter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebConfigTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void isConverterRegistryRegisteredTest() {
        assertThat(context.getBean(WebConfig.class)).isInstanceOf(WebMvcConfigurer.class);
    }

    @Test
    void isConverterRegisteredTest() {
        ConversionService conversionService = context.getBean(ConversionService.class);
//        assertThat(conversionService.canConvert(String.class, Integer.class)).isTrue();
//        assertThat(conversionService.canConvert(Integer.class, String.class)).isTrue();
        assertThat(conversionService.canConvert(IpPort.class, String.class)).isTrue();
        assertThat(conversionService.canConvert(String.class, IpPort.class)).isTrue();

//        assertThat(conversionService.convert("1,000", Number.class)).isEqualTo(1000L);
//        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
    }
}
