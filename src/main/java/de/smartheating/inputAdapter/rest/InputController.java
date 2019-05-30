package de.smartheating.inputAdapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import de.smartheating.SmartHeatingCommons.communication.RepositoryClient;
import de.smartheating.SmartHeatingCommons.exceptions.ProfileNotSetException;
import de.smartheating.SmartHeatingCommons.persistedData.Device;
import de.smartheating.SmartHeatingCommons.persistedData.SensorEvent;
import io.swagger.annotations.ApiOperation;

@RestController
public class InputController {

	Logger logger = LoggerFactory.getLogger(InputController.class);
	
	@Autowired
	RepositoryClient repoClient;

	@PostMapping(value = "/device", produces = "application/json")
	@ApiOperation(value = "This event prepares a device for usage")
	public ResponseEntity<?> prepareDevice(@RequestBody Device device) {
		try {
			Device preparedDevice = repoClient.addDevice(device);
			logger.info("Got request to add a new device with the name: " + device.getDeviceName());
			return new ResponseEntity<>(preparedDevice, HttpStatus.OK);
		} catch (RestClientException e) {
			logger.error("Connection with Repository-Service failed");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfileNotSetException e) {
			logger.error("Spring-Profile not set");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping(value = "/event", produces = "application/json")
	@ApiOperation(value = "This endpoint prepares an incoming event for processng")
	public ResponseEntity<?> prepareEvent(@RequestBody SensorEvent event) {
		logger.info("Got request to prepare an event for processing");
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
}
