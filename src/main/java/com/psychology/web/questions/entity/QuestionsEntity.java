package com.psychology.web.questions.entity;


import com.psychology.web.customer.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "questions")
public class QuestionsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "question")
	private String question;
	@Column(name = "response")
	private String response;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
}
