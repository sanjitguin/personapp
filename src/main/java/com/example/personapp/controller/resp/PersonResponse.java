package com.example.personapp.controller.resp;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.personapp.vo.PersonVO;

public class PersonResponse extends BaseResponse<PersonVO> {

	private List<PersonVO> data;
	
	@Override
	public List<PersonVO> getData() {
		return this.data;
	}
	
	public void setData(List<PersonVO> data) {
		this.data = data;
	}
	
	public PersonResponse(HttpStatus status) {
		super(status);
	}
	
	public PersonResponse() {
		// TODO Auto-generated constructor stub
	}

	
}
