package my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.entity.User;
import my.secret.SecretBody;
import my.service.UserService;
import my.vo.Result;
import my.vo.UserVO;

@SecretBody // 使用该注解 标注当前类后才会进行加解密处理，没有标注的类不会进行加解密处理
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO) throws Exception {
        logger.info("/user/add - request [ userVO = {} ]", userVO);
        
        User entity = userVO.toEntity();
        User data = userService.add(entity); // 返回带主键id的用户实例
        Result result = new Result();
        result.setData(data.toVO());
        
        logger.info("/user/add - response [ result = {} ]", result);
        return result;
    }
}
