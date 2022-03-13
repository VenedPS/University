package ua.com.foxminded.university.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "group_courses", 
        joinColumns = @JoinColumn(name = "group_id"), 
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<StudentEntity> students;
    
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @Filter(
            name = "groupLessons", 
            condition = "groupId = :id"
        )
    private List<LessonEntity> lessons;

    public GroupEntity() {

    }

    public GroupEntity(Integer id, String name, List<CourseEntity> courses, List<StudentEntity> students, List<LessonEntity> lessons) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.students = students;
        this.lessons = lessons;
    }

    public GroupEntity(GroupEntity group) {
        this.id = group.getId();
        this.name = group.getName();
        this.courses = new ArrayList<CourseEntity>(group.getCourses());
        this.students = new ArrayList<StudentEntity>(group.getStudents());
        this.lessons = new ArrayList<LessonEntity>(group.getLessons());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
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
        result = prime * result + ((courses == null) ? 0 : courses.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((students == null) ? 0 : students.hashCode());
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
        GroupEntity other = (GroupEntity) obj;
        if (courses == null) {
            if (other.courses != null)
                return false;
        } else if (!courses.equals(other.courses))
            return false;
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (students == null) {
            if (other.students != null)
                return false;
        } else if (!students.equals(other.students))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GroupEntity [id=" + id + ", name=" + name + ", courses=" + courses + ", students=" + students
                + ", lessons=" + lessons + "]";
    }

    
}
