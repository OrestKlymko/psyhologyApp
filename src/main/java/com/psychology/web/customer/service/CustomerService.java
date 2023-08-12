package com.psychology.web.customer.service;


import com.psychology.web.customer.checker.CustomerIsValid;
import com.psychology.web.customer.entity.CustomerEntity;
import com.psychology.web.customer.repo.CustomerRepository;
import com.psychology.web.questions.entity.QuestionsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);

//	public CustomerEntity getById(CustomerEntity customer) {
//		CustomerEntity customerEntity = customerRepository.findById(customer.getId()).get();
//		if (customerEntity != null) {
//			return customerEntity;
//		} else {
//			throw new RuntimeException("Not found");
//		}
//	}


	public void registration(CustomerEntity customer) {
		if (customerRepository.getCustomerEntityByEmail(customer.getEmail()) != null) {
			throw new RuntimeException("Customer already exists");
		}
		if (CustomerIsValid.check(customer)) {

			customerRepository.save(customer);
		} else {
			throw new RuntimeException("Not valid password or email");
		}
	}

	public QuestionsEntity saveQuestion(String savedQuestions, CustomerEntity customer, String response) {
		QuestionsEntity questionsEntity = new QuestionsEntity();
		questionsEntity.setQuestion(savedQuestions);
		questionsEntity.setCustomer(customer);
		questionsEntity.setResponse(response);

		List<QuestionsEntity> questionsList = customer.getQuestionsList();
		questionsList.add(questionsEntity);
		customer.setQuestionsList(questionsList);
		return questionsEntity;
	}
}
