package com.cos.instagram.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.instagram.web.dto.JoinReqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	private String name;
	private String email;		// 모델 만들기 때 실수로 안 넣음
	private String website;
	private String bio;	// 자기소개
	private String phone;
	private String gender;
	private String profileImage;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private String provider;
	private String providerId;
	@CreationTimestamp
	private Timestamp createDate;
	

}
