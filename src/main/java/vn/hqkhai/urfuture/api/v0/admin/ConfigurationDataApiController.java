package vn.hqkhai.urfuture.api.v0.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.urfuture.openapi.api.ConfigurationDataApi;
import vn.hqkhai.urfuture.openapi.model.ConfigurationModel;
import vn.hqkhai.urfuture.service.ConfigurationService;

@RestController
public class ConfigurationDataApiController implements ConfigurationDataApi {

	@Autowired
	private ConfigurationService configurationService;
	
	@Override
	public ResponseEntity<ConfigurationModel> getConfiguration() {
		return ResponseEntity.ok(configurationService.getConfigurationData());
	}
	
}
