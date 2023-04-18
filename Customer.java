package com.clarit.hs.service.items;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("customers")
public class Customer extends RepresentationModel<Customer> implements Serializable {

	private String id;
	@NotEmpty(message = "name should null or empty")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 15 characters")
	private String name;
	@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	@Pattern(regexp = "(0|91)?[6-9][0-9]{9}",message = "Invalid mobile number")
	private String phone;
	@NotEmpty(message = "address should be empty")
	private String address;




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public void setLinks(List<Link> links) {
		super.removeLinks();
		super.add(links);
	}

}
