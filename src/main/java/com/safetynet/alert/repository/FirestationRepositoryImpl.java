package com.safetynet.alert.repository;





import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.exception.SafetyNetExceptions.DuplicateFirestationException;
import com.safetynet.alert.exception.SafetyNetExceptions.FirestationNotFoundException;
import com.safetynet.alert.model.Firestation;



import lombok.extern.log4j.Log4j2;

import static com.safetynet.alert.util.JsonDataExtractor.extractDataFromJson;


@Log4j2
@Repository
@Primary
public class FirestationRepositoryImpl implements FirestationRepository {

	private final List<Firestation> listOfAllFirestations;

	@Autowired
	public FirestationRepositoryImpl() {
		this.listOfAllFirestations = extractDataFromJson().getFirestations();
	}

	@Override
	public List<String> findAddressByFirestationNumber(String firestationNumber) {
		log.debug("findAddressByFirestationNumber() {}", firestationNumber);
		List<String> addressHandledByFirestation = new ArrayList<>();

		for (Firestation firestation : listOfAllFirestations) {
			if (firestation.getStation().equals(firestationNumber)) {
				addressHandledByFirestation.add(firestation.getAddress());
			}
		}

		if (addressHandledByFirestation.isEmpty()) {
			log.error("FirestationNotFoundException");
			throw new FirestationNotFoundException("No fire station found with the specified station number");
		}

		log.debug("Completed findAddressByFirestationNumber() {}", firestationNumber);
		return addressHandledByFirestation;
	}

	@Override
	public String findFirestationNumberByAddress(String address) {
		log.debug("findFirestationNumberByAddress() {}", address);
		String firestationNumber = null;

		for (Firestation firestation : listOfAllFirestations) {
			if (firestation.getAddress().equals(address)) {
				firestationNumber = firestation.getStation();
				break;
			}
		}

		if (firestationNumber == null) {
			log.error("FirestationNotFoundException");
			throw new FirestationNotFoundException("No fire station found covering the specified Address");
		}

		log.debug("Completed findFirestationNumberByAddress() {}", address);
		return firestationNumber;
	}

	@Override
	public Firestation addFirestation(Firestation firestationToAdd) {
		log.debug("addFirestation() {}", firestationToAdd);

		if (listOfAllFirestations.contains(firestationToAdd)) {
			log.error("DuplicateFirestationException");
			throw new DuplicateFirestationException("Duplicate firestation found");
		}

		listOfAllFirestations.add(firestationToAdd);

		log.info("Completed addFirestation() {}", firestationToAdd);
		return firestationToAdd;
	}

	@Override
	public Firestation updateFirestationByAddress(String addressCoveredByFirestation, String firestationNumberToUpdate) {
		log.debug("updateFirestationByAddress() {} {}", addressCoveredByFirestation, firestationNumberToUpdate);
		Firestation updatedFirestation = null;

		for (Firestation firestation : listOfAllFirestations) {
			if (firestation.getAddress().equals(addressCoveredByFirestation)) {
				firestation.setStation(firestationNumberToUpdate);
				updatedFirestation = new Firestation(firestation.getAddress(), firestation.getStation());
				break;
			}
		}

		if (updatedFirestation == null) {
			log.error("FirestationNotFoundException");
			throw new FirestationNotFoundException("No firestation found with the specified address");
		}

		log.info("Completed updateFirestationByAddress() {} {}", addressCoveredByFirestation, firestationNumberToUpdate);
		return updatedFirestation;
	}

	@Override
	public Firestation removeFirestationByAddress(String firestationCoveredAddress) {
		log.debug("removeFirestationByAddress() {}", firestationCoveredAddress);
		Firestation firestationToDelete = null;

		for (Firestation firestation : listOfAllFirestations) {
			if (firestation.getAddress().equals(firestationCoveredAddress)) {
				firestationToDelete = new Firestation(firestation.getAddress(), firestation.getStation());
				listOfAllFirestations.remove(firestation);
				break;
			}
		}

		if (firestationToDelete == null) {
			log.error("FirestationNotFoundException");
			throw new FirestationNotFoundException("No firestation found with the specified address");
		}

		log.info("Completed removeFirestationByAddress() {}", firestationCoveredAddress);
		return firestationToDelete;
	}
}

