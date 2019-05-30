package de.smartheating.inputAdapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.smartheating.SmartHeatingCommons.persistedData.Device;
import de.smartheating.SmartHeatingCommons.persistedData.SensorEvent;
import io.swagger.annotations.ApiOperation;

@RestController
public class InputController {

	Logger logger = LoggerFactory.getLogger(InputController.class);

	@PostMapping(value = "/device", produces = "application/json")
	@ApiOperation(value = "This event prepares a device for usage")
	public ResponseEntity<?> prepareDevice(@RequestBody Device device) {
		logger.info("Got request to add a new device with the name: " + device.getDeviceName());
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
	
	@PostMapping(value = "/event", produces = "application/json")
	@ApiOperation(value = "This endpoint prepares an incoming event for processng")
	public ResponseEntity<?> prepareEvent(@RequestBody SensorEvent event) {
		logger.info("Got request to prepare an event for processing");
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
}
