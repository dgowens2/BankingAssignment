import com.tiy.Bank;
import com.tiy.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by DTG2 on 10/09/16.
 */
public class BankTests {

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void checkCustomerList() {
        Bank testBank = new Bank();

        for (Customer customer : testBank.getCustomerList()) {
            System.out.println(testBank.getCustomerList().indexOf(customer) + " " + customer.getUserName());
        }

//        assertNotNull(testBank.getCustomerList());
    }


}
