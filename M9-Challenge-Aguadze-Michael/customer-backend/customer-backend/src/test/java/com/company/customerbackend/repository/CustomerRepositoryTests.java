package com.company.customerbackend.repository;

import com.company.customerbackend.model.Customer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {
    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void createCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Kiki");
        customerTest.setLastName("Palmer");
        customerTest.setState("CA");

        customerTest = customerRepository.save(customerTest);

        Optional<Customer> custom = customerRepository.findById(customerTest.getId());

        assertEquals(custom.get(), customerTest);

        assertTrue(custom.isPresent());
    }

    @Test
    public void updateCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Michael");
        customerTest.setLastName("Jordan");
        customerTest.setState("WA");

        customerTest = customerRepository.save(customerTest);

        customerTest.setFirstName("Michael");
        customerTest.setLastName("Jordan");
        customerTest.setState("WA");

        customerRepository.save(customerTest);

        Optional<Customer> custom = customerRepository.findById(customerTest.getId());
        assertEquals(custom.get(), customerTest);
    }

    @Test
    public void deleteCustomer() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Taraji");
        customerTest.setLastName("Helson");
        customerTest.setState("NY");

        customerTest = customerRepository.save(customerTest);

        Optional<Customer> custom = customerRepository.findById(customerTest.getId());

        assertEquals(custom.get(), customerTest);

        customerRepository.deleteById(customerTest.getId());

        custom = customerRepository.findById(customerTest.getId());

        assertFalse(custom.isPresent());
    }


    @Test
    public void getCustomerByState() {

        Customer customerTest = new Customer();
        customerTest.setFirstName("Tiwa");
        customerTest.setLastName("Savage");
        customerTest.setState("GA");

        customerTest = customerRepository.save(customerTest);

        Customer customerTest2 = new Customer();
        customerTest2.setFirstName("Tiwa");
        customerTest2.setLastName("Savage");
        customerTest2.setState("TX");

        customerTest2 = customerRepository.save(customerTest2);

        Customer customerTest3 = new Customer();
        customerTest3.setFirstName("Jordin");
        customerTest3.setLastName("Sparks");
        customerTest3.setState("VA");

        customerTest3 = customerRepository.save(customerTest3);

        Customer customerTest4 = new Customer();
        customerTest4.setFirstName("Nicki");
        customerTest4.setLastName("Minaj");
        customerTest4.setState("VA");

        customerTest4 = customerRepository.save(customerTest4);



        //All customer from GA
        List<Customer> customersList = customerRepository.findByState("GA");
        assertEquals( 1, customersList.size());
        assertTrue(customersList.contains(customerTest));

        //All customer from VA
        List<Customer> customersList2 = customerRepository.findByState("VA");
        assertEquals(2, customersList2.size());
        assertTrue(customersList2.contains(customerTest3));
        assertTrue(customersList2.contains(customerTest4));
    }

}
