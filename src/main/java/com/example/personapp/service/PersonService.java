package com.example.personapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.personapp.model.PersonModel;
import com.example.personapp.model.PersonRepository;
import com.example.personapp.vo.PersonVO;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repo;
	
	@Transactional(readOnly = true)
	public List<PersonVO> getAllPerson() {
		List<PersonModel> data = repo.findAll();
		if(CollectionUtils.isEmpty(data)) {
			return new ArrayList<>();
		}
		List<PersonVO> personsData = new ArrayList<>();
		data.forEach(item -> {
			PersonVO vo = PersonMapper.convert(item);
			personsData.add(vo);
		});
		return personsData;
		
	}
	
	@Transactional(readOnly = true)
	public Optional<PersonVO> getPerson(Long id) {
		Optional<PersonModel> data = repo.findById(id);
		if(!data.isPresent()) {
			return Optional.empty();
		}
		PersonVO v = PersonMapper.convert(data.get());
		return Optional.of(v);
		
	}
	
	private Optional<PersonVO> createPerson(PersonVO vo) {
		PersonModel model = PersonMapper.convert(vo);
		model = repo.save(model);
		return Optional.of(PersonMapper.convert(model));
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Optional<PersonVO> updateOrCreatePerson(PersonVO vo) {
		if(Objects.isNull(vo.getId())) {
			return createPerson(vo);
		}
		Optional<PersonModel> optModel = repo.findById(vo.getId());
		if(!optModel.isPresent()) {
			throw new RuntimeException("Id not found");
		}
		PersonModel model = optModel.get();
		model.setName(vo.getName());
		model.setAddress(vo.getAddress());
		System.out.println("update old" + model.getVersion());
		model = repo.save(model);
		return Optional.of(PersonMapper.convert(model));
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deletePerson(Long id) {
		if(Objects.isNull(id)) {
			return false;
		}
		Optional<PersonModel> data = repo.findById(id);
		System.out.println("delete" + data.get().getVersion());
		repo.delete(data.get());
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public PersonVO withdrawlAmmount(long personId, long amm) {
		Optional<PersonModel> modelOpt = repo.findById(personId);
		if(!modelOpt.isPresent()) {
			throw new RuntimeException("Person not exists");
		}
		PersonModel model = modelOpt.get();
		if (model.getAmmount()-amm <0) {
			throw new RuntimeException("withdrawl amount can't exceeded");
		}
		model.setAmmount(model.getAmmount()-amm);
		// hold transaction to test locking example
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		repo.save(model);
		return PersonMapper.convert(model);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public PersonVO depositAmmount(long personId, long amm) {
		Optional<PersonModel> modelOpt = repo.findById(personId);
		if(!modelOpt.isPresent()) {
			throw new RuntimeException("Person not exists");
		}
		PersonModel model = modelOpt.get();
		model.setAmmount(model.getAmmount()+amm);
		// hold transaction to test locking example
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		repo.save(model);
		return PersonMapper.convert(model);
	}
}
