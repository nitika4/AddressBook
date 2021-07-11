package com.reece.addressbook.service;

import java.util.Arrays;
import java.util.List;

import com.reece.addressbook.model.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressServiceTest {

	private AddressService addressService;

	@Before
	public void init() {
		addressService = new AddressService();
	}

	@Test
	public void testAddContact() throws Exception {
		Contact contact = addressService.addContact("test", new Contact("name", 1234567));
		Contact expectedContact = new Contact("name", 1234567);
		Assert.assertTrue(expectedContact.equals(contact));
		addressService.removeContact("test", contact.getId());
	}

	@Test
	public void testRemoveContact() throws Exception {
		Contact contact = addressService.addContact("test", new Contact("name", 1234567));
		addressService.removeContact("test", contact.getId());
		try {
			addressService.removeContact("test", contact.getId());
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals("Invalid contact ID"));
		}
	}

	@Test
	public void testRetrieveAllContactsFromAddressBook() throws Exception {
		Contact contact1 = addressService.addContact("test", new Contact("name", 1234567));
		Contact contact2 = addressService.addContact("test", new Contact("name", 1234567));
		List<Contact> actualContactList = addressService.retrieveContacts("test");
		List<Contact> expectedContactList = Arrays.asList(contact1, contact2);

		Assert.assertTrue(actualContactList.containsAll(expectedContactList));

	}

	@Test
	public void testAllUniqueContacts() throws Exception {

		Contact contact1 = addressService.addContact("test", new Contact("name", 1234567));
		Contact contact2 = addressService.addContact("test1", new Contact("name", 1234567));
		Contact contact3 = addressService.addContact("test", new Contact("DiffName", 1234567));

		List<Contact> contactList = addressService.retrieveAllUniqueContacts();
		Assert.assertTrue(contactList.size() == 2);

		addressService.removeContact("test", contact1.getId());
		addressService.removeContact("test1", contact2.getId());
		addressService.removeContact("test", contact3.getId());
	}

}
