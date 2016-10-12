package mockito.java.example;

import java.util.List;

/**
 * Created by Federico on 12/10/2016.
 */
public class PayRoll {

    private EmployeeDB db;
    private BankService bankService;
    public PayRoll(EmployeeDB db, BankService bankService) {
        this.db = db;

        this.bankService = bankService;
    }

    public int monthlyPayments() {
        List<Employee> employees = db.getAllEmployees();
        for (Employee employee: employees) {
            try {
                bankService.makePayment(employee.getBankId(), employee.getSalary());
                employee.setPaid(true);
            }
            catch (RuntimeException e) {
                employee.setPaid(false);
                e.printStackTrace();

            }
        }
        return employees.size();

    }
}
