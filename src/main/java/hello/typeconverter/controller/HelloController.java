package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        log.info("intValue:{}", intValue);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        log.info("data:{}", data);
        return "ok";
    }

    /**
     * {@link RequestParamMethodArgumentResolver} 의 부모 abstract class 내부
     * {@link AbstractNamedValueMethodArgumentResolver#resolveArgument} 메소드가 호출되어 ConversionService 에 등록된 StringToIpPortConverter를 해당 메소드에서 호출하여 사용한다.
     * @param ipPort
     * @return
     */
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        log.info("ipPort.getIp:{}", ipPort.getIp());
        log.info("ipPort.getPort:{}", ipPort.getPort());
        return "ok";
    }
}
