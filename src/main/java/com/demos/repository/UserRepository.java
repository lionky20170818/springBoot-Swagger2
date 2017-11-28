package com.demos.repository;

import com.demos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Function:
 * Author: created by liguoliang
 * Date: 2017/11/28 0028 下午 5:14
 * Version: 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserName(String userName);
//    User findByNameAndAge(String userName, String password);

//    @Query("from User u where u.userName=:userName")
//    User findUser(@Param("name") String userName);

}
