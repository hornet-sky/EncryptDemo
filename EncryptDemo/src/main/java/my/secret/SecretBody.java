package my.secret;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**  
 * 此注解标注的类或方法 会进行加解密处理
 */ 
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface SecretBody {

}
