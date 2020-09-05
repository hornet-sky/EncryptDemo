package my.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;

import my.util.DesCbcUtil;

@ControllerAdvice
@ConditionalOnProperty(prefix = "faster.secret", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({ SecretProperties.class })
@Order(1)
public class SecretResponseAdvice implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SecretProperties secretProperties;

    /**
           * 判断是否支持加密响应体
     *
     * @param methodParameter methodParameter
     * @return true/false
     */
    private boolean supportSecretRequest(MethodParameter methodParameter) {
        if (!secretProperties.isScanAnnotation()) {
            return true;
        }
        // 判断class是否存在注解
        if (methodParameter.getContainingClass().getAnnotation(secretProperties.getAnnotationClass()) != null) {
            return true;
        }
        // 判断方法是否存在注解
        return methodParameter.getMethodAnnotation(secretProperties.getAnnotationClass()) != null;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        logger.info("original response body: {}", body);
        logger.info("original response body class: {}", body.getClass());
        logger.info("original response body MediaType.APPLICATION_JSON: {}", selectedContentType.includes(MediaType.APPLICATION_JSON));
        if(supportSecretRequest(returnType)) {
            if(selectedContentType.includes(MediaType.APPLICATION_JSON)) {
                String originalRespBodyJson = JSON.toJSONString(body);
                String encryptedRespBody = DesCbcUtil.encode(originalRespBodyJson, secretProperties.getDesSecretKey(), secretProperties.getDesIv());
                logger.info("original response body json: {}", originalRespBodyJson);
                logger.info("encrypted response body: {}", encryptedRespBody);
                ((ServletServerHttpResponse) response).getHeaders().setContentType(MediaType.TEXT_PLAIN);
                return encryptedRespBody;
            }
        }
        return body;
    }
}
