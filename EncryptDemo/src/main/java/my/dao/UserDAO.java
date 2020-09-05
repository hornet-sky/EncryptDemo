package my.dao;

import org.springframework.stereotype.Repository;

import my.entity.User;

@Repository
public class UserDAO {
    public User save(User entity) {
        // 模拟保存到数据库并生成主键id
        Integer id = 1001;
        entity.setId(id);
        return entity;
    }
}
