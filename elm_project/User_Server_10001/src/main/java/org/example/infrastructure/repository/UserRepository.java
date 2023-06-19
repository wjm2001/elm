package org.example.infrastructure.repository;

import org.example.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsernameAndPassword(String username, String password);

    public User findUserById(Long userId);

    public User findUserByUsername(String username);

//    @Modifying
//    @Transactional
//    @Query(value = "update user set userImg=? where id=?",nativeQuery = true)
//    public Integer updateImgById(String userImg,Long id);
}

