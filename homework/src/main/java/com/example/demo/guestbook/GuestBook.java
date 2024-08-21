package com.example.demo.guestbook;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
public class GuestBook {
	private int num;
	private String writer;
	private String pwd;
	private Date wdate;
	private String content;
	
}
