package vn.hqkhai.babymeal.api.v0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.babymeal.openapi.api.ConfigurationDataApi;
import vn.hqkhai.babymeal.openapi.model.ConfigurationModel;
import vn.hqkhai.babymeal.service.ConfigurationService;

@RestController
public class ConfigurationDataApiController implements ConfigurationDataApi {

	@Autowired
	private ConfigurationService configurationService;
	
	@Override
	public ResponseEntity<ConfigurationModel> getConfiguration() {
		return ResponseEntity.ok(configurationService.getConfigurationData());
	}
	
}
