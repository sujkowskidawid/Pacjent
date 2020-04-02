import java.awt.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static Scanner scanner;
    private static PatientService patientService;
    private static List<Patient> patientList;

    //TODO Dopisać możliwość usunięcia z rejestru pacjenta oraz dodać pole do Pacjenta z ceną wizyty

    public static void main(String[] args) {
        ApachePOIExcelRead apachePOIExcelRead = new ApachePOIExcelRead();
        patientList = apachePOIExcelRead.getPatientList();
        patientService = new PatientService(patientList);
        scanner = new Scanner(System.in);
        System.out.println("Wybierz akcje: \n0 - Zakończ działanie \n1 - Sprawdź czy pacjent jest zarejestrowany \n2 - Zarejestruj pacjenta \n3 - Sprawdź cenę wizyty dla danego pacjenta \n4 - Usuń pacjenta z rejestru");
        Integer action = scanner.nextInt();
        chooseTypeSearching(action);
    }

    private static void chooseTypeSearching(Integer typeNumber) {
        switch (typeNumber) {
            case 0:
                break;
            case 1:
                isRegistered();
                break;
            case 2:
                registerPatient();
                System.out.println("Udało się zarejestrować nowego pacjenta");
                System.out.println(patientList);
                break;
            case 3:
                checkPrice();


            case 4:
                deletePatient();
                System.out.println("Udało się usunąć pacjenta z rejestru");
                System.out.println(patientList);
                break;

            default:
                break;
        }
    }

    private static void registerPatient() {

        System.out.println("W celu zarejestrowania pacjenta podaj: Imię, nazwisko, PESEL oraz cenę wizyty:");
        String name = scanner.next();
        String surname = scanner.next();
        BigInteger pesel = scanner.nextBigInteger();
        Double price = scanner.nextDouble();

        Patient patient = new Patient(name, surname, pesel, price);
        patientList.add(patient);

        ApachePOIExcelWrite apachePOIExcelWrite = new ApachePOIExcelWrite();
        apachePOIExcelWrite.createExcel(patientList);
        //TODO dopisać rejestracje pacjenta
    }

    private static void checkPrice() {

        System.out.println("Aby sprawdzić cenę podaj: \n1 - Imię i nazwisko \n2 - PESEL ");
        Integer action = scanner.nextInt();
        switch (action) {
            case 1:
                String name = scanner.next();
                String surname = scanner.next();
                System.out.println("Cena wizyty tego pacjenta wynosi: " + patientService.checkPrice(name, surname));
            case 2:
                BigInteger pesel = scanner.nextBigInteger();
                System.out.println("Cena wizyty tego pacjenta wynosi: " + patientService.checkPrice(pesel));

        }

    }

    private static void deletePatient() {
        System.out.println("W celu usunięcia pacjenta podaj imię, nazwisko oraz PESEL osoby, którą chcesz usunąć z rejestru: ");
        String name = scanner.next();
        String surname = scanner.next();
        BigInteger pesel = scanner.nextBigInteger();
        patientService.deletePatient(name,surname,pesel);
        System.out.println("Udało Ci się usunąć pacjenta z rejestru");

    }

    private static void isRegistered() {
        System.out.println("Sprawdź czy pacjent jest zarejestrowany po: \n0 - Zakończ działanie \n1 - imieniu i nazwisku \n2 - numerze PESEL");
        Integer action = scanner.nextInt();

        switch (action) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj imię: ");
                String name = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String surname = scanner.next();
                if(patientList.contains(name) && patientList.contains(surname)){
                    System.out.println("Pacjent o takim imieniu i nazwisku już występuje, proszę o podanie nr PESEL: ");
                    String pesel = scanner.next();
                    if(patientList.contains(pesel)){
                        System.out.println("Pacjent o takich danych jest już zarejestrowany!");
                    }
                    else{System.out.println(patientService.isRegistered(new BigInteger(pesel))); }
                }
                else{
                System.out.println(patientService.isRegistered(name, surname));}
                break;
            //TODO W przypadku dopasowań więcej niż 1 rzucić użytkownikowi błąd
            case 2:
                System.out.println("Podaj pesel: ");
                String pesel = scanner.next();
                if(patientList.contains(pesel)){
                    System.out.println("Pacjent o takich danych jest już zarejestrowany!");
                }
                else{
                System.out.println(patientService.isRegistered(new BigInteger(pesel)));}
                break;
            default:
                break;
        }
    }


}
