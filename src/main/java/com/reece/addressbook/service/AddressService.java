package com.reece.addressbook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.reece.addressbook.model.Contact;

@Service
public class AddressService {
	
	static Map<String, List<Contact>> addressBooksMap = new HashMap<String, List<Contact>>();
	
	static {
		addressBooksMap.put("Business", new ArrayList<Contact>());
		addressBooksMap.put("Personal", new ArrayList<Contact>());
	}

	public Contact addContact(String addressBookId, Contact contact) throws Exception {
		contact.setId(Long.toString(System.currentTimeMillis()));
		if(addressBooksMap.containsKey(addressBookId)) {
			addressBooksMap.get(addressBookId).add(contact);
			} else {
				addressBooksMap.put(addressBookId, Stream.of(contact).collect(Collectors.toList()));
			}
		return contact;
	}

	public Contact removeContact(String addressBookId, String contactId) throws Exception {
		if (addressBooksMap.containsKey(addressBookId)) {
			Optional<Contact> contact = addressBooksMap.get(addressBookId).stream()
					.filter(it -> contactId.equals(it.getId())).findFirst();
			Contact ct = contact.orElseThrow(() -> new Exception("Invalid contact ID"));

			addressBooksMap.get(addressBookId).remove(ct);
			return ct;

		} else {
			throw new Exception("Invalid AddressBook Id");
		}
	}

	public List<Contact> retrieveContacts(String addressBookId) throws Exception {
		if (addressBooksMap.containsKey(addressBookId)) {
			return addressBooksMap.get(addressBookId);
		}
		throw new Exception("Invalid AddressBook Id");
	}

	public List<Contact> retrieveAllUniqueContacts() {
		return addressBooksMap.values().stream().flatMap(List::stream).distinct().collect(Collectors.toList());
	}

}
