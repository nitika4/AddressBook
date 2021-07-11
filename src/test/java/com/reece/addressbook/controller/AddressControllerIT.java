package com.reece.addressbook.controller;

import com.reece.addressbook.service.AddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.reece.addressbook.AddressApplication;
import com.reece.addressbook.model.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerIT {
	
	@LocalServerPort
    private int port;

    @Autowired
    private AddressService addressService;

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testAddContact() throws Exception {
        Contact contact = new Contact("Name", 123456789);
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<Contact> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contact"),
                HttpMethod.POST, entity, Contact.class);
        System.out.println(response.getStatusCode().value());
        Assert.assertTrue(response.getStatusCode().value() == 200);

        addressService.removeContact("test", response.getBody().getId());
    }

    @Test
    public void testRemoveContact() throws Exception {
    
        Contact contact = addressService.addContact("test", new Contact("Name", 123456789));
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contact/"+contact.getId()),
                HttpMethod.DELETE, new HttpEntity<String>(null, null), String.class);

        Assert.assertEquals(response.getStatusCode().value(), 200);

    }

    @Test
    public void testRetrieveAllContactsFromAddressBook() throws Exception {
    	addressService.addContact("test", new Contact("Name", 123456789));
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/addressBook/test/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void testRetrieveAllUniqueContacts() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

}
