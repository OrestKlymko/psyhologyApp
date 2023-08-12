package com.psychology.web.customer.controller;

import com.psychology.web.GPT.ChatGPT;
import com.psychology.web.questions.entity.QuestionsEntity;
import com.psychology.web.customer.entity.CustomerEntity;
import com.psychology.web.customer.service.CustomerService;
import com.psychology.web.questions.repo.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@SessionAttributes({"questions", "response"})

public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuestionsRepository questionsRepository;

	@GetMapping("/questions")
	public ModelAndView get() {
		return new ModelAndView("questions");
	}

	@PostMapping("/form")
	public ModelAndView registrationForm(@RequestParam("flexRadioDefault") String value, Model model) throws Exception {
		model.addAttribute("questions", value);
		return new ModelAndView("registrationForm");
	}

	@PostMapping("/createuser")
	public ModelAndView post(@ModelAttribute("questions") String savedQuestions,
	                         CustomerEntity customer,
	                         Model model) throws Exception {
		String response = ChatGPT.chatGPT(savedQuestions);
		model.addAttribute("response", response);
		QuestionsEntity questionsEntity = customerService.saveQuestion(savedQuestions, customer, response);
		customerService.registration(customer);
		questionsRepository.save(questionsEntity);

		return new ModelAndView("redirect:/responseGPT");
	}

	@PostMapping("/skip-registration")
	public ModelAndView skipRegistration(@ModelAttribute("questions") String savedQuestions,
	                                     Model model) throws Exception {
		String response = ChatGPT.chatGPT(savedQuestions);
		model.addAttribute("response", response);
		return new ModelAndView("redirect:/responseGPT");
	}


	@GetMapping("/createuser")
	public ModelAndView getUser() {
		return new ModelAndView("registrationForm");
	}

	@GetMapping("/responseGPT")
	public ModelAndView getResponse(@ModelAttribute("response") String response) {
		ModelAndView modelAndView = new ModelAndView("responseChatGPT");
		modelAndView.addObject("responseGPT", response);
		return modelAndView;
	}

}
