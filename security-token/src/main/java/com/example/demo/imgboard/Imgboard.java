package com.example.demo.imgboard;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Imgboard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Users writer;
	private String title;
	private String img;
}
