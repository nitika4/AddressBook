package com.reece.addressbook.controller;

import java.util.List;

import com.reece.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reece.addressbook.model.Contact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="AddressBookAPIs", tags = "addressbook",produces=MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/api/addressBook/{addressBookId}/contact")
	@ApiOperation("Adds a contact to an addressbook")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = Contact.class)})
    public Contact addContact(
            @PathVariable String addressBookId, @RequestBody Contact contact) throws Exception {

        return addressService.addContact(addressBookId, contact);
    }

    @DeleteMapping("/api/addressBook/{addressBookId}/contact/{contactId}")
    @ApiOperation("Deletes a contact from an addressbook")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = Contact.class)})
    public Contact removeContact(@PathVariable String addressBookId,
                                                @PathVariable String contactId) throws Exception {
        return addressService.removeContact(addressBookId, contactId);
    }
    
    @GetMapping("/api/addressBook/{addressBookId}/contacts")
    @ApiOperation("Fetches all contact in an addressbook")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = Contact.class, responseContainer = "List")})
    public List<Contact> fetchContacts(@PathVariable String addressBookId) throws Exception {
        return addressService.retrieveContacts(addressBookId);
    }

    @GetMapping("/api/unique-contacts")
    @ApiOperation("Fetches all unique contacts from all addressbooks")
	@ApiResponses(value= {@ApiResponse(code=200, message = "OK", response = Contact.class, responseContainer = "List")})
    public List<Contact> retrieveAllUniqueContactsFromAllAddressBooks() {
        return addressService.retrieveAllUniqueContacts();
    }

}
