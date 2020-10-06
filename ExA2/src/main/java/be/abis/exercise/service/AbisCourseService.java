package be.abis.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.MemoryCourseRepository;

public class AbisCourseService implements CourseService {
	
	@Autowired
	MemoryCourseRepository m ;
	
	@Override
	public List<Course> findAllCourses() {
		return m.findAllCourses();	
	}

	@Override
	public Course findCourse(int id) {
		return m.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return m.findCourse(shortTitle);
	}

}
