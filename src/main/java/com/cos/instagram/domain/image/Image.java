package com.cos.instagram.domain.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;
	private String caption;
	private String imageUrl;
	
	// Image를 select 하면 한명의 User가 딸려옴. 부하가 적음. Image image = imageRepository.findById(1); 했을 때 user를 이미 가져옴 (FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER)	// Image와 User의 관계
	@JoinColumn(name="userId") // 컬럼명. 타입은 User 오브젝트의 PK의 타입
	private User user;	// ORM 쓰면 DB에는 userId가 들어가면서 select할 때는 join해서 User를 가져옴
	
	// Image를 select 하면 여러개의 Tag가 딸려옴. 부하가 큼. Image image = imageRepository.findById(1); 했을 때까지는 tags를 안 가져 오고, image.getTags() 했을 때 비로소 가져옴 (FetchType.LAZY)
	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY)	// Image와 Tag의 관계. tag는 Image 테이블에 foreign 키로 들어가지 않도록! Tag 테이블이 imageId를 foreign 키로 들고 있음
	@JsonIgnoreProperties({"image"})	// Jackson이 getter로 가져올 때 Image와 Tag가 서로 참조로 인한 무한참조 방지
	private List<Tag> tags;			// mappedBy에는 연관관계 주인의 변수명을 적는다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
