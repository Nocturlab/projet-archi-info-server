package fr.nocturlab.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.nocturlab.repository.ParkingRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParkingManager {
	private static String ENCODE = "HmacSHA256";
	private static ParkingManager instance;

	public ParkingManager() {
		ParkingManager.instance = this;
	}

	@Autowired
	private ParkingRepository parkingRepository;


	/**
	 * @return the instance
	 */
	public static ParkingManager getInstance() {
		return instance;
	}

	/**
	 * @return the parkingRepository
	 */
	public ParkingRepository getParkingRepository() {
		return parkingRepository;
	}
}