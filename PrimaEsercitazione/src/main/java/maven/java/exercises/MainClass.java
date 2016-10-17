package maven.java.exercises;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by federicoschipani on 16/10/2016.
 */
public class MainClass {


    public static void main(String[] args) {
        BankService bankService = mock(BankService.class);
        doThrow(new RuntimeException()).doNothing().when(bankService).makePayment(anyString(), anyInt());
        bankService.makePayment("aa", 00);
    }




}
