package project;
import static org.junit.Assert.*;
import org.junit.Test;

public class StudentTest {

    private Student student;

    @Test
    public void test() {
        student = new Student("Anton", "Misskii", 230622, 2001, 2.02);
        assertEquals(230622, student.getId());
    }
}

