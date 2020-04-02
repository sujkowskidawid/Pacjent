import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
public class PatientService {


    private List<Patient> patientList;


    public boolean isRegistered(BigInteger pesel) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public boolean isRegistered(String name, String surname) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public Double checkPrice(String name, String surname) {
        Double checkPrice = 0.0;
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                checkPrice = patient.getWallet();
            }
        }
        return checkPrice;
    }

    public Double checkPrice(BigInteger pesel) {
        Double checkPrice = 0.0;
        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                checkPrice = patient.getWallet();
            }
        }
        return checkPrice;
    }

    public void deletePatient(String name, String surname, BigInteger pesel) {
        Patient deletePatient = null;
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname) && patient.getPesel().equals(pesel)) {
                deletePatient = patient;
            }
        }
        patientList.remove(deletePatient);
    }

    public Double substractMoney(int cost){
        Double substractMoney = 0.0;
        for(Patient patient : patientList){
            substractMoney =patient.getWallet() - cost;

        }return substractMoney;

}}
