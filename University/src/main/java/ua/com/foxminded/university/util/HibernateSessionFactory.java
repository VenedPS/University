package ua.com.foxminded.university.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.entity.TeacherEntity;

public class HibernateSessionFactory {
    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);
    
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
        
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(StudentEntity.class);
                configuration.addAnnotatedClass(TeacherEntity.class);
                configuration.addAnnotatedClass(LessonEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.error("Initial SessionFactory creation failed." + e.getMessage());
            }
        }
        return sessionFactory;
    }
}
