package com.example.personapp.service;

import com.example.personapp.model.PersonModel;
import com.example.personapp.vo.PersonVO;

public class PersonMapper {

	public static PersonModel convert(PersonVO vo) {
		PersonModel model = new PersonModel();
		model.setAddress(vo.getAddress());
		model.setId(vo.getId());
		model.setName(vo.getName());
		model.setAmmount(vo.getAmmount());
		return model;
	}
	
	public static PersonVO convert(PersonModel model) {
		PersonVO vo = new PersonVO();
		vo.setAddress(model.getAddress());
		vo.setId(model.getId());
		vo.setName(model.getName());
		vo.setAmmount(model.getAmmount());
		return vo;
	}
}
