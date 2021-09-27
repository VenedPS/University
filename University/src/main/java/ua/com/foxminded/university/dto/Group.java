package ua.com.foxminded.university.dto;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Integer id;
    private String name;
    private List<Course> courses;
    private List<Student> students;

    public Group(Integer id, String name, List<Course> courses, List<Student> students) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.students = students;
    }
    
    public Group(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.courses = new ArrayList<Course>(group.getCourses());
        this.students = new ArrayList<Student>(group.getStudents());
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courses == null) ? 0 : courses.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Group other = (Group) obj;
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
        return "Group [id=" + id + ", name=" + name + ", courses=" + courses + ", students=" + students + "]";
    }
}
