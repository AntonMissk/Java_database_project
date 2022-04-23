package project;
import java.util.InputMismatchException;
import java.util.Scanner;
import  java.sql.*;



public class Main {
    public static int pouzeCelaCisla(Scanner sc) {
        int cislo = 0;
        try {
            cislo = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Nastala vyjimka typu " + e.toString());
            System.out.println("zadejte prosim cele cislo ");
            sc.nextLine();
            cislo = pouzeCelaCisla(sc);
        }
        return cislo;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Database database = new Database();

        int volba;
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println("Vyberte pozadovanou cinnost:");
            System.out.println("0...ukonceni programu");
            System.out.println("1...pridat noveho studenta");
            System.out.println("2...pridat noveho ucitele");
            System.out.println("3...zadat studentovi novou znamku");
            System.out.println("4...zjistit prumer studenta");
            System.out.println("5...odstraneni ucitele z univerzity :(");
            System.out.println("6...odstraneni studenta z univerzity :(");
            System.out.println("7...vypis vsech ucitelu danneho studenta");
            System.out.println("8...prirazeni studenta vyucujicimu");
            System.out.println("9...odebirani studenta vyucujicimu");
            System.out.println("10...zjisteni stipendia");
            System.out.println("11...stanoveni a zjisteni platu ucitelu");
            System.out.println("12...nalezeni jednotlivych osob v databazi");
            System.out.println("13...trideni podle poctu studentu");
            System.out.println("14...razeni podle stujniho prumeru");
            System.out.println("15...razeni ucitelu nebo studentu podle ABECEDY");
            System.out.println("16...zjistit celkove financni prostredky");
            System.out.println("17...vytvoreni tabulky studentu");
            System.out.println("18...vytvoreni tabulky ucitelu");
            System.out.println("19...nacteni vabraneho studenta");
            System.out.println("20...nacteni vybraneho ucitele");
            System.out.println("21...smazani studenta z databaze");
            System.out.println("22...smazani ucitele z databaze");
            System.out.println();
            volba = pouzeCelaCisla(sc);

            switch (volba) {
                case 1:
                    database.setStudentMap();
                    break;
                case 2:
                    database.setTeacherMap();
                    break;
                case 3:
                    try {
                        database.nastaveniZnamky();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        database.zjisteniPrumeru();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    database.odstraneniUcitele();
                    break;
                case 6:
                    database.odstraneniStudenta();
                    break;
                case 7:
                    try {
                        database.vypisUciteluStudenta();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        database.priradeniStudentaUcitelovi();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        database.odstraneniOdUcitelu();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                    break;
                case 10:
                    try {
                        database.zjisteniStipendia();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    try {
                        database.stanoveniPlatu();
                        database.zjisteniPlatu();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 12:
                    database.nalezeniOsoby();
                    break;
                case 13:
                    database.razeniDlePoctuStudentu();
                    break;
                case 14:
                    try {
                        database.razeniDleStudijnihoPrumeru();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }catch (InputMismatchException e){
                        e.printStackTrace();
                    }
                    break;
                case 15:
                    database.AbcRazeni();
                    break;
                case 16:
                    database.financniProstredky();
                    break;
                case 17:
                    System.out.println("Pripojeni k databazi "+ database.connect());
                    System.out.println("Vytvoreni tabulky "+ database.createTableStudenti());
                    database.insertRecordStudenti();
                    database.selectAllStudenti();
                    break;
                case 18:
                    System.out.println("Pripojeni k databazi "+ database.connect());
                    System.out.println("Vytvoreni tabulky "+ database.createTableUcitele());
                    database.insertRecordUcitele();
                    database.selectAllUcitele();
                    break;
                case 19:
                    try {
                        database.getRecordStudent();
                    }catch (InputMismatchException e){
                        e.printStackTrace();
                    }
                    break;
                case 20:
                    try {
                        database.getRecordUcitel();
                    }catch (InputMismatchException e){
                        e.printStackTrace();
                    }
                    break;
                case 21:
                    database.deleteStudent();
                    database.selectAllStudenti();
                    break;
                case 22:
                    database.deleteUcitel();
                    database.selectAllUcitele();
                    break;
                case 0:
                    run = false;
                    break;
            }

        }
        database.disconnect();
    }
}
