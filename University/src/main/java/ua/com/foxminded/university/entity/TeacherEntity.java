package ua.com.foxminded.university.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

@Entity
@Table (name = "teachers")
public class TeacherEntity extends PersonEntity {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @OneToMany(mappedBy = "teacherId", fetch = FetchType.EAGER)
    @Filter(
            name = "teacherLessons", 
            condition = "teacherId = :id"
        )
    private List<LessonEntity> lessons;

    public TeacherEntity() {
        
    }
    
    public TeacherEntity(Integer id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public TeacherEntity(TeacherEntity teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.secondName = teacher.getSecondName();
        this.birthDate = teacher.getBirthDate();
        this.address = teacher.getAddress();
        this.phone = teacher.getPhone();
        this.email = teacher.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeacherEntity other = (TeacherEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessons == null) {
            if (other.lessons != null)
                return false;
        } else if (!lessons.equals(other.lessons))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TeacherEntity [id=" + id + ", lessons=" + lessons + "]";
    }

}
