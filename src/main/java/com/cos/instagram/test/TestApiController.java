package com.cos.instagram.test;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.image.ImageRepository;
import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.tag.TagRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.domain.user.UserRole;

@RestController
public class TestApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("/test/api/join")
	public User join()  {
		User user = User.builder()
				.name("최주호")
				.password("1234")
				.phone("0102222")
				.bio("안녕 나 코스야")
				.role(UserRole.USER)
				.build();
		User userEntity = userRepository.save(user);
		return userEntity;
	}
	
	@GetMapping("/test/api/image")
	public String image() {
		User userEntity = userRepository.findById(1).get();
		
		Image image = Image.builder()
				.location("다낭")
				.caption("설명")
				.user(userEntity)
				.build();
		
		Image imageEntity = imageRepository.save(image);
		
		List<Tag> tags = new ArrayList<>();
		Tag tag1 = Tag.builder()
				.name("#다낭")
				.image(imageEntity)
				.build();

		Tag tag2 = Tag.builder()
				.name("#여행")
				.image(imageEntity)
				.build();
		
		tags.add(tag1);
		tags.add(tag2);
		
		tagRepository.saveAll(tags);
		
		// List<Image> images = imageRepository.findAll();

		
		// Image imageEntity = imageRepository.save(image);
		// imageRepository.save(image);
		// Image imageEntity = imageRepository.findById(1).get();
		return "images insert 잘됨";	// MessageConverter의 Jackson 발동. getter를 때려서 JSON으로 변환. 이 시점에서 tag 테이블에도 select가 걸림
	}
	
	@GetMapping("/test/api/image/list")
	public List<Image> imageList() {
		return imageRepository.findAll();
	}
	
	@GetMapping("/test/api/tag/list")
	public List<Tag> tagList() {
		return tagRepository.findAll();
	}
}
