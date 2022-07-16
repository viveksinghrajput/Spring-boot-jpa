package com.attra.model.book;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.attra.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
	private int id;
	private String bookname;
}
