package com.duzceuniversity.kurumtakip.DataBase.Repository;

import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll();

    User findById(int id);
    User findByUsernameAndDeleteAtIsNull(String username);

    void saveAll(List<User> users);
}
