import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Create the commonStudents() method of the MyUtils class to return a HashSet of common elements of two student lists.
 * For example, for a given list1 [Students [id=1, name=Ivan], Students [id=2, name=Petro], Students [id=3, name=Stepan]]
 * and list2 [Students [id=1, name=Ivan], Students [id=3, name=Stepan], Students [id=4, name=Andriy]]
 * you should get [Students [id=3, name=Stepan], Students [id=1, name=Ivan]].
 */
class MyUtils {
    public static class Student {
        private final int id;
        private final String name;
        private final int hash;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
            hash = Objects.hash(id, name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id &&
                    Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public String toString() {
            return String.format("Student{id=%d, name='%s'}", id, name);
        }
    }
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        Set<Student> res = new HashSet<>(list1);
        res.retainAll(list2);
        return res;
    }

    public static void main(String[] args) {
        // smoke test
        // l1 = [Students [id=1, name=Ivan], Students [id=2, name=Petro], Students [id=3, name=Stepan]]
        List<Student> list1 = List.of(new Student(1, "Ivan"),
                                      new Student(2, "Petro"),
                                      new Student(3, "Stepan"));
        // l2 = [Students [id=1, name=Ivan], Students [id=3, name=Stepan], Students [id=4, name=Andriy]]
        List<Student> list2 = List.of(new Student(1, "Ivan"),
                                      new Student(3, "Stepan"),
                                      new Student(4, "Andriy"));
        Set<Student> res = new MyUtils().commonStudents(list1, list2);
        System.out.println(res); // [Students [id=3, name=Stepan], Students [id=1, name=Ivan]]
    }
}
