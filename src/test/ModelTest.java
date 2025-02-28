package test;

import Main.Attributes.Behavior;
import Main.Attributes.Gender;
import Main.Attributes.Person;
import org.junit.jupiter.api.*;

public class ModelTest {
    @BeforeEach
    void init() {
        Person h1 = new Person("Arthur", Gender.MALE, 29);
        Person h2 = new Person("Ford", Gender.MALE, 19);
        Person h3 = new Person("Trillian", Gender.FEMALE, 23);
        Person h4 = new Person("Zaphod", Gender.MALE, 19);

        Behavior Catch = new Behavior("Catch");
        Behavior Pull = new Behavior("Pull");
        Behavior Open = new Behavior("Open");


        h3.happened(Catch);
        h3.happened(Pull);
        h2.happened(Open);
        h4.happened(Open);
    }

    @Nested
    class ClassTest {
        @Test
        @DisplayName("Test for constructor")
        void testConstructor() {
            System.out.println("Testing constructor of Person");
            Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(null, Gender.NONE, 1));
            Assertions.assertEquals("Name shouldn't be null", e1.getMessage());

            Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("Test", null, 1));
            Assertions.assertEquals("Gender shouldn't be null", e2.getMessage());

            Exception e3 = Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("TestName", Gender.NONE, -1));
            Assertions.assertEquals("Age must >= 0", e3.getMessage());
            System.out.println("Test done");
            System.out.println();

            System.out.println();

            System.out.println("Testing constructor of Behavior");
            Exception e8 = Assertions.assertThrows(IllegalArgumentException.class, () -> new Behavior(null));
            Assertions.assertEquals("Name shouldn't be null", e8.getMessage());

            System.out.println("Test done");
            System.out.println();
        }

        @Test
        @DisplayName("Test for setter")
        void testSetter() {
            System.out.println("Testing setter of Person");

            Person h = new Person();

            Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> h.setName(null));
            Assertions.assertEquals("Name shouldn't be null", e1.getMessage());

            Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, () -> h.setGender(null));
            Assertions.assertEquals("Gender shouldn't be null", e2.getMessage());

            Exception e3 = Assertions.assertThrows(IllegalArgumentException.class, () -> h.setAge(-1));
            Assertions.assertEquals("Age must >= 0", e3.getMessage());

            System.out.println("Test done");
            System.out.println();

            System.out.println();
            System.out.println("Testing setter of Behavior");

            Behavior b = new Behavior();
            Exception e9 = Assertions.assertThrows(IllegalArgumentException.class, () -> b.setName(null));
            Assertions.assertEquals("Name shouldn't be null", e9.getMessage());

            System.out.println("Test done");
            System.out.println();
        }
    }

    @Nested
    class FunctionTest {

        @Test
        @DisplayName("Test for happened()")
        void testHappened() {
            System.out.println("Testing happened() of Person");

            Person h = new Person("TestPerson", Gender.NONE, 20);
            Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> h.happened(null));
            Assertions.assertEquals("Behavior can't be null", e1.getMessage());

            System.out.println("Test done");
            System.out.println();

            System.out.println("Testing learn() of Person");

        }
    }


}