package maven.java.exercises;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by Federico on 12/10/2016.
 */
public class MockitoTest {


    @Test
    public void testIsEmptyOnSpiedArrayList() {
        List<String> list = spy(new ArrayList<>());
        when(list.isEmpty()).thenReturn(false);
        assertFalse(list.isEmpty());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOnSpiedArrayListException() {
        List<String> list = spy(new ArrayList<String>());
        /*
        il metodo get() prima dello stub viene richiamato, non essendoci
        alcun valore all'interno della lista è lecito aspettarsi
        una IndexOutOfBoundsException
         */
        when(list.get(0)).thenReturn("foo");
    }

    public void testGetOnSpiedArrayList() {
        List<String> list = spy(new ArrayList<String>());
        /*
        con doReturn si risolve il problema nel test precedente
         */
        doReturn("foo").when(list).get(0);
        assertEquals("foo", list.get(0));
    }


}
