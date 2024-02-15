package com.example.polarisdigitechassessment.data.model;

import com.example.polarisdigitechassessment.data.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "item")
public class Item extends BaseClass {
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double weight;
	
	@Column(nullable = false)
	private String code;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "box_id", referencedColumnName = "id")
	private Box box;

	public Item(Box box, ItemDto itemDto) {
		this.name = itemDto.getName();
		this.weight = itemDto.getWeight();
		this.code = itemDto.getCode();
		this.box = box;
	}


}
