package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentDao extends CrudRepository<StudentEntity, Integer>  {

	@Query("SELECT l FROM LessonEntity l WHERE l.group.id = ?1 and l.date >= ?2 and l.date <= ?3")
	public List<LessonEntity> getStudentLessons(int groupId, LocalDate startDate, LocalDate endDate);
}
