package mockito.java.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Federico on 12/10/2016.
 */
public class PayRollTest {

    private PayRoll payroll;
    private EmployeeDB db;
    private List<Employee> employees;
    private BankService bankService;
    @Before
    public void setUp() {
        employees = new ArrayList<>();
        db = mock(EmployeeDB.class);
        bankService = mock(BankService.class);
        payroll = new PayRoll(db, bankService);
        when(db.getAllEmployees()).thenReturn(employees);

    }
    @Test
    public void testOtherEmployeesArePaidInCaseOfASingleException() {
        Employee firstEmployee = spy(createTestEmployee("test1", "ID1", 1000));
        Employee secondEmployee = spy(createTestEmployee("test2", "ID2", 2000));
        employees.add(firstEmployee);
        employees.add(secondEmployee);
        doThrow(new RuntimeException()).doNothing().when(bankService).makePayment(anyString(), anyInt());
        assertNumberOfProcessedEmployees(2);
        verify(bankService, times(2)).makePayment(anyString(), anyInt());
        verify(firstEmployee, times(1)).setPaid(false);
        verify(secondEmployee, times(1)).setPaid(true);
    }
    @Test
    public void testOtherEmployeesArePaidInCaseOfASingleAlternative() {
        Employee firstEmployee = spy(createTestEmployee("test1", "ID1", 1000));
        Employee secondEmployee = spy(createTestEmployee("test2", "ID2", 2000));
        employees.add(firstEmployee);
        employees.add(secondEmployee);



        doThrow(new RuntimeException()).when(bankService).makePayment(argThat(new ArgumentMatcher<String>() {
                    @Override
                    public boolean matches(Object argument) {
                        return argument.equals("ID1");
                    }
                }),anyInt()
                );

        /*
        Oppure:
        doThrow(new RuntimeException()).when(bankService).makePayment("ID1", 1000);
         */

        assertNumberOfProcessedEmployees(2);
        verify(bankService, times(2)).makePayment(anyString(), anyInt());
        verify(firstEmployee, times(1)).setPaid(false);
        verify(secondEmployee, times(1)).setPaid(true);
    }
    @Test
    public void testEmployeeIsNotPaidWhenBankThrowsException() {
        String employeedId = "ID1";
        int salary = 1000;
        Employee testEmployee = spy(createTestEmployee("test employee", employeedId, salary));
        employees.add(testEmployee);
        /*
        per stubbare il metodo del BankService in modo da fargli restituire un eccezione
        si usa doThrow al posto di when perché si sta
        lavorando su uno spy (vedere test.mockito.java.example.MockitoTest)
         */
        doThrow(new RuntimeException()).when(bankService).makePayment(employeedId, salary);

        assertNumberOfProcessedEmployees(1);
        verify(bankService, times(1)).makePayment(employeedId, salary);
        verify(testEmployee, times(1)).setPaid(false);
    }


    @Test
    public void testEmployeePaidIsUpdated(){
        String employeeId = "ID1";
        int salary = 1000;
        Employee testEmployee = spy(createTestEmployee("test employee", employeeId, salary));
        employees.add(testEmployee);
        assertNumberOfProcessedEmployees(1);
        verify(bankService, times(1)).makePayment(employeeId, salary);
        verify(testEmployee).setPaid(true);
    }
    @Test
    public void testInteractionOrder() {
        String employeeID = "ID1";
        int salary = 1000;
        employees.add(createTestEmployee("test employee", employeeID,salary));
        assertNumberOfProcessedEmployees(1);
        InOrder inOrder = inOrder(db, bankService);
        inOrder.verify(db).getAllEmployees();
        inOrder.verify(bankService).makePayment(employeeID,salary);
    }

    @Test
    public void testAllEmployeesArePayed() {
        employees.add(createTestEmployee("TestEmployee1", "ID1", 1000));
        employees.add(createTestEmployee("TestEmployee2", "ID2", 2000));
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> salaryCaptor = ArgumentCaptor.forClass(Integer.class);
        assertNumberOfProcessedEmployees(2);
        verify(bankService, times(2)).makePayment(idCaptor.capture(), salaryCaptor.capture());
        assertEquals("ID1", idCaptor.getAllValues().get(0));
        assertEquals("ID2", idCaptor.getAllValues().get(1));
        assertEquals(1000, salaryCaptor.getAllValues().get(0).intValue());
        assertEquals(2000, salaryCaptor.getAllValues().get(1).intValue());
    }
    @Test
    public void testEmployeeIsPayed() {
        String id_employee = "ID_1";
        int salary = 1000;
        employees.add(createTestEmployee("TestEmployee", "ID_1", 1000));
        assertNumberOfProcessedEmployees(1);
        verify(bankService, times(1)).makePayment(id_employee, salary);
    }

    private Employee createTestEmployee(String testEmployee, String bankId, int salary) {
        return new Employee(testEmployee, bankId, salary);
    }

    @Test
    public void testNoEmployees() {

        assertNumberOfProcessedEmployees(0);
    }
    @Test
    public void testSingleEmployee() {

        employees.add(new Employee());

        assertNumberOfProcessedEmployees(1);
    }
    @Test
    public void testOnlyOneInteractionWithDB() {
        payroll.monthlyPayments();
        verify(db, times(1)).getAllEmployees();
    }
    private void assertNumberOfProcessedEmployees(int expected) {
        int numberOfPayments = payroll.monthlyPayments();
        assertEquals(expected, numberOfPayments);
    }


}