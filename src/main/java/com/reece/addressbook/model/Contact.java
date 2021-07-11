package com.reece.addressbook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {

	private String id;
	
	private String name;
	private long phoneNumber;
	
	public Contact(String name, long phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
        if (obj == null || getClass() != obj.getClass()) {
        	return false;
        }
        
		Contact ct = (Contact)obj;
		System.out.println(ct.getName());
		System.out.println(this.getName());
		System.out.println(ct.getPhoneNumber());
		System.out.println(this.getPhoneNumber());
		if((ct.getName().equals(this.getName())) && (ct.getPhoneNumber() == this.getPhoneNumber())) {
			return true;
		}
		return false;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }
	
}
