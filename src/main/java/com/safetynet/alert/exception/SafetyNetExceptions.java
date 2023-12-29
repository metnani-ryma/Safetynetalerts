package com.safetynet.alert.exception;

public class SafetyNetExceptions  {




	    public static class DuplicateFirestationException extends RuntimeException {
	        public DuplicateFirestationException() {
	            super("Duplicate firestation found");
	        }

	        public DuplicateFirestationException(String message) {
	            super(message);
	        }
	    }

	    public static class DuplicateMedicalRecordException extends RuntimeException {
	        public DuplicateMedicalRecordException(String message) {
	            super(message);
	        }
	    }

	    public static class DuplicatedPersonException extends RuntimeException {
	        public DuplicatedPersonException() {
	            super("Duplicate person found");
	        }

	        public DuplicatedPersonException(String message) {
	            super(message);
	        }
	    }

	    public static class FirestationNotFoundException extends RuntimeException {
	        public FirestationNotFoundException() {
	            super("Fire station not found");
	        }

	        public FirestationNotFoundException(String message) {
	            super(message);
	        }
	    }


	    public class IncompleteRequestException extends RuntimeException{
	        public IncompleteRequestException(){
	            super("Incomplete request");
	        }
	        public IncompleteRequestException(String message){
	            super(message);
	        }
	    }

	    public class MedicalRecordNotFoundException extends RuntimeException{
	        public MedicalRecordNotFoundException() {
	            super("Medical Record not found");
	        }

	        public MedicalRecordNotFoundException(String message) {
	            super(message);
	        }
	    }

	    public class PersonNotFoundException extends RuntimeException{
	        public PersonNotFoundException() {
	            super("Person not found");
	        }

	        public PersonNotFoundException(String message) {
	            super(message);
	        }
	    }





	}

