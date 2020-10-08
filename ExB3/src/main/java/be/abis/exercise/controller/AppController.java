package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	
	Person loggedPerson;
	List<Person> personsFound;
	Person personFound;
	List<Course> courseFound;
	Person removedPerson;
	Person addedPerson;
	
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
	
	@GetMapping("/searchforcourse")
	public String showSearchForCourse(Model model) {
		return "searchforcourse";
	}
	
	@GetMapping("/personadmin")
	public String showPersonAdmin(Model model) {
		return "personadmin";
	}
	
	@GetMapping("/back")
	public String back() {
		return "redirect:/welcomepage";
	}

	@GetMapping("/changepassword")
	public String showChangePassword(Model model) {
		model.addAttribute("person", loggedPerson);
		return "changepassword";
	}
	
	@PostMapping("/changepassword")
	public String submitNewPassword(Model model, Person person) {
		try {
			trainingService.changePassword(loggedPerson, person.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/passwordmodified";
	}
	
	@GetMapping("/passwordmodified")
	public String showPasswordModified(Model model) {
		return "passwordmodified";
	}
	
	@GetMapping("/searchpersons")
	public String showSearchPersons(Model model) {
		return "searchpersons";
	}
	
	@GetMapping("/addperson")
	public String showAddPerson(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "addperson";
	}
	
	@PostMapping("/addperson")
	public String submitNewPerson(Model model, Person person) {
		try {
			trainingService.addPerson(person);
			} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/addedperson";
	}
	
	@GetMapping("/addedperson")
	public String showAddedPerson(Model model) {
		return "addedperson";
	}
	
	
	@GetMapping("/removeperson")
	public String showRemovePerson(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "removeperson";
	}
	
	@PostMapping("/removeperson")
	public String submitRemovePerson(Model model, Person person) {
		trainingService.deletePerson(person.getPersonId());
		return "redirect:/removedperson";
	}
	
	@GetMapping("/removedperson")
	public String showRemovedPerson(Model model) {
		return "removedperson";
	}
	
	@GetMapping("/backtoadmin")
	public String backtoadmin() {
		return "redirect:/personadmin";
	}
	
	@GetMapping("/backtosearchpersons")
	public String backtosearchpersons() {
		return "redirect:/searchpersons";
	}
	
	@GetMapping("/allpersons")
	public String showAllPersons(Model model) {
		personsFound = trainingService.getAllPersons();
		model.addAttribute("persons", personsFound);
		return "allpersons";
	}
		
	@GetMapping("/searchpersonbyid")
	public String showSearchPersonById(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "searchpersonbyid";
	}
	
	@PostMapping("/searchpersonbyid")
	public String submitSearchPersonById(Model model, Person person) {
		personFound = trainingService.findPerson(person.getPersonId());
		return "redirect:/resultpersonbyid";
	}
	
	@GetMapping("/resultpersonbyid")
	public String showResultPersonById(Model model) {
		model.addAttribute("person", personFound);
		return "/resultpersonbyid";
	}
	
}
