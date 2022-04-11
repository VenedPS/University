package ua.com.foxminded.university.dao.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.university.dao.CustomizedTeacherDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

@Repository
@Transactional
public class TeacherDaoImpl implements CustomizedTeacherDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    private final Logger logger = LoggerFactory.getLogger(TeacherDaoImpl.class);

    @Override
    public List<LessonEntity> getTeacherLessons(TeacherEntity teacherEntity, LocalDate startDate, LocalDate endDate)
            throws LessonNotFoundException {
        
        logger.info("Start getting teacher lessons with teacherId={} from startDate={} to endDate={}", teacherEntity.getId(),
                startDate.toString(), endDate.toString());
        
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            lessons = teacherEntity.getLessons()
                    .stream()
                    .filter(lesson -> lesson.getDate().isAfter(startDate))
                    .filter(lesson -> lesson.getDate().isBefore(endDate))
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException(
                    String.format("Empty lessons list for teacherId=%d from startDate=%s to endDate=%s", teacherEntity.getId(),
                            startDate.toString(), endDate.toString()));
        }
        logger.info("All teacher lessons from DB was read!");
        return lessons;
    }

}
