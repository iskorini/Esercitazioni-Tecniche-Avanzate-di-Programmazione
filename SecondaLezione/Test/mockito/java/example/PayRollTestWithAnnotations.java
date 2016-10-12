package mockito.java.example;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Federico on 12/10/2016.
 */

public class PayRollTestWithAnnotations {

    @InjectMocks  private PayRoll payRoll;

    @Mock private EmployeeDB employeeDB;
    @Mock private BankService bankService;

    /*
    Per mockare i campi si possono usare diverse strategie:
         - MockitoAnnotations.initMocks(this);
         - @Rule
            public MockitoRule rule = MockitoJUnit.rule();
         - @RunWith(MockitoJUnitRunner.class)
     */


    List<Employee> employees;

    @Before
    public void init() {
        employees = new ArrayList<>();
        MockitoAnnotations.initMocks(this);
        when(employeeDB.getAllEmployees()).thenReturn(employees);

        /*
        grazie a @InjectMocks
        non è più necessario istanziare manualmente il payRoll
         */
    }



}
