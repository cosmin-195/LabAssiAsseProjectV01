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
    void testSaveStudentCheckName() {
        service.saveStudent("1","Jerry",934);
        Assertions.assertEquals(service.findAllStudents().iterator().next().getID(),"1");
        service.deleteStudent("1");
    }

    @Test
    void testSaveStudentCheckId() {
        service.saveStudent("1","Jerry",934);
        Assertions.assertEquals(service.findAllStudents().iterator().next().getNume(),"Jerry");
        service.deleteStudent("1");
    }
}