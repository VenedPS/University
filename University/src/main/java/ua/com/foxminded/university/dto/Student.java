package ua.com.foxminded.university.dto;

public class Student {
    private Integer id;
    private Integer groupId;
    private Person person;

    public Student(Integer id, Person person) {
        this.id = id;
        this.person = person;
    }
    
    public Student(Student student) {
        this.id = student.getId();
        this.groupId = student.getGroupId();
        this.person = student.getPerson();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((person == null) ? 0 : person.hashCode());
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
        Student other = (Student) obj;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (person == null) {
            if (other.person != null)
                return false;
        } else if (!person.equals(other.person))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", groupId=" + groupId + ", person=" + person + "]";
    }
}
