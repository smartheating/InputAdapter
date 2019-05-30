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
import de.smartheating.SmartHeatingCommons.persistedData.Event;
import de.smartheating.inputAdapter.rabbitmq.MessageProducer;
import io.swagger.annotations.ApiOperation;

@RestController
public class InputController {

	Logger logger = LoggerFactory.getLogger(InputController.class);
	
	@Autowired
	RepositoryClient repoClient;
	@Autowired
	MessageProducer producer;

	@PostMapping(value = "/device", produces = "application/json")
	@ApiOperation(value = "This event prepares a device for usage")
	public ResponseEntity<?> prepareDevice(@RequestBody Device device) {
		try {
			logger.info("Got request to prepare a new device with the name: " + device.getDeviceName());
			Device preparedDevice = repoClient.addDevice(device);
			return new ResponseEntity<>(preparedDevice.getId(), HttpStatus.OK);
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
	public ResponseEntity<?> prepareEvent(@RequestBody Event event) {
		try {
			logger.info("Got request to prepare an event for processing");
			Event preparedEvent = repoClient.addEvent(event);
			producer.sendEvent(preparedEvent);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RestClientException e) {
			logger.error("Connection with Repository-Service failed");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfileNotSetException e) {
			logger.error("Spring-Profile not set");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
