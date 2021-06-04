package vn.hqkhai.urfuture.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.hqkhai.urfuture.entity.Age;
import vn.hqkhai.urfuture.entity.Translation;
import vn.hqkhai.urfuture.exception.ObjectNotFoundException;
import vn.hqkhai.urfuture.openapi.model.AgeModel;
import vn.hqkhai.urfuture.repository.AgeRepository;
import vn.hqkhai.urfuture.utils.ModelMapperUtils;

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
		age.setName(ModelMapperUtils.mapList(ageModel.getName(), Translation.class));
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
		age.setName(ModelMapperUtils.mapList(ageModel.getName(), Translation.class));
		return ModelMapperUtils.mapObject(repository.save(age), AgeModel.class);
	}

	@Transactional
	public void deleteAge(String ageId) {
		repository.deleteById(ageId);
	}
	
}
