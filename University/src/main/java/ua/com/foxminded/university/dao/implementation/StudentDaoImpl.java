package ua.com.foxminded.university.dao.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import ua.com.foxminded.university.dao.CustomizedStudentDao;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;

public class StudentDaoImpl implements CustomizedStudentDao {

    @Autowired
    private GroupDao groupDao;
    
    private final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    @Override
    public List<LessonEntity> getStudentLessons(StudentEntity studentEntity, LocalDate startDate, LocalDate endDate)
            throws LessonNotFoundException {
        logger.info("Start getting student lessons with studentId={} from startDate={} to endDate={}", studentEntity.getId(),
                startDate.toString(), endDate.toString());
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            lessons = groupDao.findById(studentEntity.getGroup().getId()).get()
                    .getLessons().stream()
                    .filter(lesson -> lesson.getDate().isAfter(startDate))
                    .filter(lesson -> lesson.getDate().isBefore(endDate))
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException(
                    String.format("Empty lessons list for studentId=%d from startDate=%s to endDate=%s", studentEntity.getId(),
                            startDate.toString(), endDate.toString()));
        }
        logger.info("All student lessons from DB was read!");
        return lessons;
    }

}
