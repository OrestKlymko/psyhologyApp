package com.psychology.web.customer.entity;


import com.psychology.web.questions.entity.QuestionsEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	@OneToMany (mappedBy = "customer")
	@ToString.Exclude
	private List<QuestionsEntity> questionsList = new ArrayList<>();
}
