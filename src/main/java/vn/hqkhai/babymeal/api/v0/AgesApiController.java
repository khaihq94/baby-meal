package vn.hqkhai.babymeal.api.v0;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.babymeal.openapi.api.AgesApi;
import vn.hqkhai.babymeal.openapi.model.AgeModel;
import vn.hqkhai.babymeal.service.AgeService;

@RestController
public class AgesApiController implements AgesApi {

	private AgeService ageService;
	
	public AgesApiController(AgeService ageService) {
		this.ageService = ageService;
	}
	
	@Override
	public ResponseEntity<AgeModel> createAge(AgeModel ageModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ageService.createAge(ageModel));
	}
	
	@Override
	public ResponseEntity<List<AgeModel>> getAllAges() {
		return ResponseEntity.ok(ageService.getAllAges());
	}
	
	@Override
	public ResponseEntity<AgeModel> findAgeById(String ageId) {
		return ResponseEntity.ok(ageService.findAgeById(ageId));
	}
	
	@Override
	public ResponseEntity<AgeModel> updateAge(String ageId, AgeModel ageModel) {
		return ResponseEntity.ok(ageService.updateAge(ageId, ageModel));
	}
	
	@Override
	public ResponseEntity<Void> deleteAge(String ageId) {
		ageService.deleteAge(ageId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
