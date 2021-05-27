package vn.hqkhai.babymeal.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.hqkhai.babymeal.entity.Age;
import vn.hqkhai.babymeal.entity.Translation;
import vn.hqkhai.babymeal.exception.ObjectNotFoundException;
import vn.hqkhai.babymeal.openapi.model.AgeModel;
import vn.hqkhai.babymeal.repository.AgeRepository;
import vn.hqkhai.babymeal.utils.ModelMapperUtils;

@Service
public class AgeService {

	private AgeRepository repository;
	
	public AgeService(AgeRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public List<AgeModel> getAllAges() {
		return ModelMapperUtils.mapList(repository.findAll(), AgeModel.class);
	}

	@Transactional
	public AgeModel createAge(AgeModel ageModel) {
		Age age = new Age();
		age.setId(UUID.randomUUID().toString());
		age.setNames(ModelMapperUtils.mapList(ageModel.getNames(), Translation.class));
		return ModelMapperUtils.mapObject(repository.insert(age), AgeModel.class);
	}

	@Transactional
	public AgeModel findAgeById(String ageId) {
		Optional<Age> ageOptional = repository.findById(ageId);
		if(!ageOptional.isPresent()) {
			throw new ObjectNotFoundException("ageId");
		}
		return ModelMapperUtils.mapObject(ageOptional.get(), AgeModel.class);
	}

	@Transactional
	public AgeModel updateAge(String ageId, AgeModel ageModel) {
		Optional<Age> ageOptional = repository.findById(ageId);
		if(!ageOptional.isPresent()) {
			throw new ObjectNotFoundException("ageId");
		}
		Age age = ageOptional.get();
		age.setNames(ModelMapperUtils.mapList(ageModel.getNames(), Translation.class));
		return ModelMapperUtils.mapObject(repository.save(age), AgeModel.class);
	}

	@Transactional
	public void deleteAge(String ageId) {
		repository.deleteById(ageId);
	}
	
}
