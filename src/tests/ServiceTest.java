package tests;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.Iterator;

class ServiceTest {

    private Service service;
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studentiTest.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "temeTest.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "noteTest.xml");

    @BeforeEach
    void setUp() {
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void testSaveStudentCheckNameNonNull() {
        service.saveStudent("1",null,934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckNameNonEmpty() {
        service.saveStudent("1","",934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckNameNotEmpty() {
        service.saveStudent("1",null,934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckSpecialChars() {
        service.saveStudent("1","Baro$an777",934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckStartingLetterUpper() {
        service.saveStudent("1","grigore",934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }


    @Test
    void testSaveStudentCheckIdNonNull() {
        service.saveStudent(null,"Jerry",934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckIdNotEmpty() {
        service.saveStudent("","Jerry",934);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckIdUnique() {
        service.saveStudent("1","Jerry",934);
        service.saveStudent("1","Definitely Not Jerry",934);
        Iterator<Student> it = service.findAllStudents().iterator();
        it.next();
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckGroupGreaterThan0() {
        service.saveStudent("1","Jerry",-1);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckGroupTooBig() {
        service.saveStudent("1","Jerry",1000);
        Assertions.assertFalse(service.findAllStudents().iterator().hasNext());
        service.deleteStudent("1");
    }
}