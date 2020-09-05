package my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    // 启动服务后访问 http://127.0.0.1:8080/userRegister.html
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
