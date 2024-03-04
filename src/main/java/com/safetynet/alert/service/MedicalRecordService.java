package com.safetynet.alert.service;



import com.safetynet.alert.model.MedicalRecord;

public interface MedicalRecordService {
    MedicalRecord postNewMedicalRecord(MedicalRecord newMedicalRecordToPost);

    MedicalRecord putMedicalRecord(MedicalRecord updatedMedicalRecord);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}