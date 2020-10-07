package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "login";
	}
	
	@PostMapping("/welcomepage")
	public String submitLogin(Model model, Person person) {
		Person loggedPerson = trainingService.findPerson(person.getEmailAddress(), person.getPassword());
		return "welcomepage";
	}

}
