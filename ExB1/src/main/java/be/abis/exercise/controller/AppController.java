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
	
	Person loggedPerson;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "login";
	}
	
	@PostMapping("/")
	public String submitLogin(Model model, Person person) {
		loggedPerson = trainingService.findPerson(person.getEmailAddress(), person.getPassword());
		return "redirect:/welcomepage";
	}
	
	@GetMapping("/welcomepage")
	public String showWelcome(Model model) {
		model.addAttribute("person", loggedPerson);
		return "welcomepage";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

}
