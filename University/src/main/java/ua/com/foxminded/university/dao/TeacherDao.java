package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;

public interface TeacherDao extends CrudRepository<TeacherEntity, Integer> {
	
	@Query("SELECT l FROM LessonEntity l WHERE l.teacher.id = ?1 and l.date >= ?2 and l.date <= ?3")
	public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate);

}
