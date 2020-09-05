package my.secret;

import java.lang.annotation.Annotation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "faster.secret")
public class SecretProperties {
    /**
           * 是否开启
     */
    private boolean enabled;
    /**
           * 是否扫描注解
     */
    private boolean scanAnnotation;
    /**
           * 扫描自定义注解
     */
    private Class<? extends Annotation> annotationClass = SecretBody.class;
    /**
     * 3des 密钥长度不得小于24
     */
    private String desSecretKey = "b2c17b46e2b1415392aab5a82869856c";
    /**
     * 3des IV向量必须为8位
     */
    private String desIv = "61960842";
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isScanAnnotation() {
        return scanAnnotation;
    }
    public void setScanAnnotation(boolean scanAnnotation) {
        this.scanAnnotation = scanAnnotation;
    }
    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }
    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }
    public String getDesSecretKey() {
        return desSecretKey;
    }
    public void setDesSecretKey(String desSecretKey) {
        this.desSecretKey = desSecretKey;
    }
    public String getDesIv() {
        return desIv;
    }
    public void setDesIv(String desIv) {
        this.desIv = desIv;
    }
}
