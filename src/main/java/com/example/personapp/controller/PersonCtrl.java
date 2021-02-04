package com.example.personapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personapp.controller.resp.PersonResponse;
import com.example.personapp.service.PersonService;
import com.example.personapp.vo.PersonVO;

@RestController
@RequestMapping(value = "/person")
public class PersonCtrl {
	
	@Autowired
	PersonService service;

	@GetMapping
	public ResponseEntity<PersonResponse> getPersons(){
		PersonResponse resp = new PersonResponse(HttpStatus.OK);
		try {
			List<PersonVO> vos = service.getAllPerson();
			resp.setData(vos);
			resp.setSuccess(true);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> getPerson(@PathVariable(name = "id") Long id){
		PersonResponse resp = new PersonResponse(HttpStatus.OK);
		try {
			Optional<PersonVO> vos = service.getPerson(id);
			if(vos.isPresent())
				resp.setData(Arrays.asList(vos.get()));
			else
				resp.setMessage("Id not found");
			resp.setSuccess(true);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@PostMapping
	public ResponseEntity<PersonResponse> createOrUpdatePerson(@RequestBody PersonVO vo){
		PersonResponse resp = new PersonResponse(HttpStatus.CREATED);
		try {
			Optional<PersonVO> v = service.updateOrCreatePerson(vo);
			resp.setData(Arrays.asList(v.get()));
			resp.setSuccess(true);
		}catch (ObjectOptimisticLockingFailureException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage("Transaction failed, locking failure");
			resp.setSuccess(false);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@PutMapping
	public ResponseEntity<PersonResponse> updatePerson(@RequestBody PersonVO vo){
		PersonResponse resp = new PersonResponse(HttpStatus.CREATED);
		try {
			Optional<PersonVO> v = service.updateOrCreatePerson(vo);
			resp.setData(Arrays.asList(v.get()));
			resp.setSuccess(true);
		}catch (ObjectOptimisticLockingFailureException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage("Transaction failed, locking failure");
			resp.setSuccess(false);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> deletePerson(@PathVariable("id") Long id){
		PersonResponse resp = new PersonResponse(HttpStatus.ACCEPTED);
		try {
			if(service.deletePerson(id))
				resp.setMessage("deleted");
			else
				resp.setMessage("Id not found");
			resp.setSuccess(true);
		}catch (ObjectOptimisticLockingFailureException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage("Transaction failed, locking failure");
			resp.setSuccess(false);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@PutMapping(value = "/{id}/withdrawl/{ammount}")
	public ResponseEntity<PersonResponse> withdrawlAmmount(@PathVariable("id") long personId,
			@PathVariable("ammount") long ammount) {
		PersonResponse resp = new PersonResponse(HttpStatus.ACCEPTED);
		try {
			PersonVO v = service.withdrawlAmmount(personId, ammount);
			resp.setData(Arrays.asList(v));
			resp.setMessage("withdrawl success");
			resp.setSuccess(true);
		}catch (ObjectOptimisticLockingFailureException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage("Transaction failed, locking failure");
			resp.setSuccess(false);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	@PutMapping(value = "/{id}/deposit/{ammount}")
	public ResponseEntity<PersonResponse> depositAmmount(@PathVariable("id") long personId,
			@PathVariable("ammount") long ammount) {
		PersonResponse resp = new PersonResponse(HttpStatus.ACCEPTED);
		try {
			PersonVO v = service.depositAmmount(personId, ammount);
			resp.setData(Arrays.asList(v));
			resp.setMessage("deposit success");
			resp.setSuccess(true);
		}catch (ObjectOptimisticLockingFailureException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage("Transaction failed, locking failure");
			resp.setSuccess(false);
		}catch (RuntimeException e) {
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
		}
		return new ResponseEntity<PersonResponse>(resp, resp.getStatus());
	}
	
	
	
	
	
	
}
