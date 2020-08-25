package com.cos.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepostory를 extends 하면 @Repository 어노테이션은 필요 없음. IoC 자동으로 됨
public interface UserRepository extends JpaRepository<User, Integer> {

}
