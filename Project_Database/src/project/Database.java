package project;


import java.sql.*;
import java.util.*;

public class Database {


    private Scanner sc;
    private Map<Integer, Student> studentMap;
    private Map<Integer, Teacher> teacherMap;
    private double celStipendia;
    private double celMzda;
    private Connection conn;


    public Database() {
        studentMap = new HashMap<>();
        teacherMap = new HashMap<>();
        sc = new Scanner(System.in);

    }

    public void setTeacherMap() throws InputMismatchException {
        System.out.println("------Vytvoreni noveho ucitele------");
        System.out.println("Uvedte jmeno ucitele: ");
        String name = sc.next();
        System.out.println("Uvedte primeni noveho ucitele: ");
        String surname = sc.next();
        System.out.println("Uvedte ID ucitele: ");
        Integer id = sc.nextInt();
        System.out.println("Uvedte rok narozeni ucitele: ");
        Integer birthdate = sc.nextInt();

        teacherMap.put(id, new Teacher(name, surname, id, birthdate));

    }


    public void setStudentMap() throws InputMismatchException {
        System.out.println("------Vytvoreni noveho studenta------");
        System.out.println("Uvedte jmeno studenta: ");
        String name = sc.next();
        System.out.println("Uvedte primeni studenta: ");
        String surname = sc.next();
        System.out.println("Uvedte ID studenta: ");
        Integer id = sc.nextInt();
        System.out.println("Uvedte rok narozeni studenta: ");
        Integer birthdate = sc.nextInt();

//        System.out.println("1...pridani novemu ucitelovi");
//        System.out.println("0...ukonceni");
//        int k = sc.nextInt();
//        while (k != 0) {
//            System.out.println("Uvedte id ucetelu ke kteremu chcete priradit studenta");
//            Integer idt = sc.nextInt();
//            teacherMap.get(idt).setStudents(id, name, surname, birthdate);
//            System.out.println("1...pridani novemu ucitelovi");
//            System.out.println("0...ukonceni");
//            k = sc.nextInt();
//        }

        studentMap.put(id, new Student(name, surname, id, birthdate, 0.0));
    }


    public void odstraneniOdUcitelu() throws NullPointerException, InputMismatchException {
        System.out.println("Uvedte id ucitele: ");
        int id = sc.nextInt();

        System.out.println("Uvedte id studenta: ");
        int ids = sc.nextInt();
        teacherMap.get(id).odstraneniStudenta(ids);
        System.out.println("Odstranili jste studenta s jmenem " + studentMap.get(ids).getName() + " a ID: " + studentMap.get(ids).getId());


    }


    public void nalezeniOsoby() {
        System.out.println("Uvedte ID osoby, kterou chcete najit: ");
        int id = sc.nextInt();
        if (studentMap.containsKey(id)) {
            System.out.println("-----------------");
            System.out.println("Vypis studenta s ID: " + studentMap.get(id).getId());
            System.out.println("Jmeno a prijmeni studenta: " + studentMap.get(id).getName() + " " + studentMap.get(id).getSurname());
            System.out.println("Rok narozeni: " + studentMap.get(id).getBirthdate());
            System.out.println("Stipendia, krerou studenat dostava: " + studentMap.get(id).getStipendia() + " korun");
            System.out.println("-----------------");
        } else if (teacherMap.containsKey(id)) {
            System.out.println("-----------------");
            System.out.println("Vypis ucitele s ID: " + teacherMap.get(id).getId());
            System.out.println("Jmeno a prijmeni ucitele: " + teacherMap.get(id).getName() + " " + teacherMap.get(id).getSurname());
            System.out.println("Rok narozeni: " + teacherMap.get(id).getBirthdate());
            System.out.println("Mzda, krerou ucitel dostava: " + teacherMap.get(id).getSalary() + " korun");
            System.out.println("-----------------");
        } else System.err.println("Zadna osoba nema ID " + id);

    }

    public void priradeniStudentaUcitelovi() throws NullPointerException,InputMismatchException {
        System.out.println("1...pridani novemu ucitelovi");
        System.out.println("0...ukonceni");
        int k = sc.nextInt();
        while (k != 0) {
            System.out.println("Uvedte ID studenta, ke kteremu chece priradit ucitele: ");
            int id = sc.nextInt();
            System.out.println("Uvedte ID ucetelu ke kteremu chcete priradit studenta: ");
            int idt = sc.nextInt();
            studentMap.get(id).vypocetPrumeru();
            teacherMap.get(idt).setStudents(studentMap.get(id).getId(), studentMap.get(id).name, studentMap.get(id).getSurname(),
                    studentMap.get(id).getBirthdate(), studentMap.get(id).getStudijniPrumer());

            System.out.println("1...pridani novemu ucitelovi");
            System.out.println("0...ukonceni");
            k = sc.nextInt();
        }

    }

    public void stanoveniPlatu() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte ID ucitelu, kteremu chcete stanovit plat: ");
        int id = sc.nextInt();
        teacherMap.get(id).setSalary();
    }


    public void zjisteniPlatu() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte ID ucitelu, plat ktereho chcete zjistit: ");
        int id = sc.nextInt();

        System.out.println("Plat ucitele " + teacherMap.get(id).getName() + " je " + teacherMap.get(id).getSalary());
    }


    public void nastaveniZnamky() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte id studenta,kteremu chcete pridat znamku: ");
        Integer id = sc.nextInt();
        System.out.println("Uvedte znamku: ");
        double znamka = sc.nextDouble();


        try {
            studentMap.get(id).setZnamka(znamka);
        } catch (NullPointerException e) {
            System.err.println("Neexsituje student s ID " + id);
            e.printStackTrace();
        }

    }

    public void zjisteniPrumeru() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte id studenta,prumer ktereho chcete zjistit: ");
        Integer id = sc.nextInt();
        studentMap.get(id).vypocetPrumeru();
        System.out.println("Prumer se rovna" + studentMap.get(id).getStudijniPrumer());

    }


    public void odstraneniStudenta() throws InputMismatchException {
        System.out.println("Uvedte ID studenta, ktereho chcete odstranit: ");
        int id = sc.nextInt();
        Iterator<Integer> iter = studentMap.keySet().iterator();
        while (iter.hasNext()) {
            Integer klic = iter.next();
            if (studentMap.get(klic).getId() == id)
                iter.remove();
            else System.err.println("Neexistuje student s ID " + id);

        }
    }

    public void odstraneniUcitele() throws InputMismatchException {
        System.out.println("Uvedte ID ucitele, ktereho chcete odstranit: ");
        int id = sc.nextInt();
        Iterator<Integer> iter = teacherMap.keySet().iterator();
        while (iter.hasNext()) {
            Integer klic = iter.next();
            if (teacherMap.get(klic).getId() == id)
                iter.remove();
            else System.err.println("Neexsituje ucitel s ID " + id);

        }
    }


    public void vypisUciteluStudenta() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte id studenta pro vypis ucitelu: ");
        int id = sc.nextInt();
        Set<Integer> klice = teacherMap.keySet();
        for (Integer klic : klice) {
            if (teacherMap.get(klic).jeStudent(id))
                System.out.println(teacherMap.get(klic).getName());
        }
    }

    public void zjisteniStipendia() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte id studenta, kteremu checte priradit stipendia: ");
        int id = sc.nextInt();
        studentMap.get(id).vypocetStipendia();
        System.out.println("Student " + studentMap.get(id).getName() + " ma stipendium: " + studentMap.get(id).getStipendia() + " korun.");
    }


    public void razeniDlePoctuStudentu() {
        Set<Map.Entry<Integer, Teacher>> entrySet = teacherMap.entrySet(); // vratim vsechny objekty z teacherMap
        List<Map.Entry<Integer, Teacher>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Teacher>>() {
            @Override
            public int compare(Map.Entry<Integer, Teacher> o1, Map.Entry<Integer, Teacher> o2) {
                return o2.getValue().getPocetStudentu() - o1.getValue().getPocetStudentu();
            }
        });

        System.out.println("Vypis setrideniho listu: ");
        list.forEach(e -> {
            System.out.println(e.getKey() + "\t" + e.getValue());
        });

    }

    public void razeniDleStudijnihoPrumeru() throws NullPointerException,InputMismatchException {
        System.out.println("Uvedte ID ucitelu, studenty ktereho musi byt trideni");
        int id = sc.nextInt();
        teacherMap.get(id).trideniStudentu();
    }

    public void AbcRazeni() {
        System.out.println("Vyberte koho checete vypsat: ");
        System.out.println("0...vypis vsech ucitelu podle abecedy");
        System.out.println("1...vypis vsech studentu podle abecedy");
        int volba;
        volba = sc.nextInt();
        switch (volba) {
            case 0:
                Set<Map.Entry<Integer, Teacher>> entrySet = teacherMap.entrySet();
                List<Map.Entry<Integer, Teacher>> list = new ArrayList<>(entrySet);
                Collections.sort(list, new Comparator<Map.Entry<Integer, Teacher>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Teacher> o1, Map.Entry<Integer, Teacher> o2) {
                        return o1.getValue().getName().compareTo(o2.getValue().getName());
                    }
                });
                System.out.println();
                System.out.println("---------");
                System.out.println("Vypis setrideniho listu: ");
                list.forEach(e -> {
                    System.out.println(e.getKey() + "\t" + e.getValue());

                });
                System.out.println("--------");
                System.out.println();
                break;
            case 1:
                Set<Map.Entry<Integer, Student>> entrySet1 = studentMap.entrySet();
                List<Map.Entry<Integer, Student>> list1 = new ArrayList<>(entrySet1);
                Collections.sort(list1, new Comparator<Map.Entry<Integer, Student>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Student> o1, Map.Entry<Integer, Student> o2) {
                        return o1.getValue().getName().compareTo(o2.getValue().getName());
                    }
                });
                System.out.println();
                System.out.println("---------");
                System.out.println("Vypis setrideniho listu: ");
                list1.forEach(e -> {
                    System.out.println(e.getKey() + "\t" + e.getValue());

                });
                System.out.println("--------");
                System.out.println();
                break;

        }

    }

    public void financniProstredky() {

        Set<Integer> klice = studentMap.keySet();
        for (Integer klic : klice) {
            studentMap.get(klic).vypocetPrumeru();
            studentMap.get(klic).vypocetStipendia();
            celStipendia += studentMap.get(klic).getStipendia();
        }
        System.out.println("Vseho potrebujeme na stipendium " + getCelStipendia());
        Set<Integer> klice2 = teacherMap.keySet();
        for (Integer klic : klice2) {
            teacherMap.get(klic).setSalary();
            celMzda += teacherMap.get(klic).getSalary();
        }
        System.out.println("Vseho potrebujeme na mzdu " + getCelMzda());
        System.out.println("Celkove financni prostredky: " + (getCelMzda() + getCelStipendia()));
    }

    public double getCelStipendia() {
        return celStipendia;
    }

    public double getCelMzda() {
        return celMzda;
    }


    public boolean connect() {
        conn= null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void disconnect() {
        if (conn != null) {
            try {     conn.close();  }
            catch (SQLException ex) { System.out.println(ex.getMessage()); }
        }
    }


    public boolean createTableStudenti()
    {
        if (conn==null)
            return false;
        String sql = "CREATE TABLE IF NOT EXISTS studenti (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL,"+ "prijmeni varchar(255), " + "narozeni integer, " + "prumer real"+");";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void insertRecordStudenti( ) {
        Set<Integer> klice = studentMap.keySet();
        for (Integer klic : klice) {
            String sql = "INSERT INTO studenti(id,jmeno,prijmeni,narozeni,prumer) VALUES(?,?,?,?,?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, studentMap.get(klic).getId());
                pstmt.setString(2, studentMap.get(klic).getName());
                pstmt.setString(3, studentMap.get(klic).getSurname());
                pstmt.setInt(4, studentMap.get(klic).getBirthdate());
                pstmt.setDouble(5, studentMap.get(klic).getStudijniPrumer());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void selectAllStudenti(){
        String sql = "SELECT id, jmeno,prijmeni,narozeni,prumer FROM studenti";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("jmeno") + "\t" +
                        rs.getString("prijmeni") + "\t" +
                        rs.getInt("narozeni") + "\t" +
                        rs.getDouble("prumer"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean createTableUcitele()
    {
        if (conn==null)
            return false;
        String sql = "CREATE TABLE IF NOT EXISTS ucitele (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL,"+ "prijmeni varchar(255), " + "narozeni integer, " + "plat real"+");";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void insertRecordUcitele( ) {
        Set<Integer> klice = teacherMap.keySet();
        for (Integer klic : klice) {
            String sql = "INSERT INTO ucitele (id,jmeno,prijmeni,narozeni,plat) VALUES(?,?,?,?,?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, teacherMap.get(klic).getId());
                pstmt.setString(2, teacherMap.get(klic).getName());
                pstmt.setString(3, teacherMap.get(klic).getSurname());
                pstmt.setInt(4, teacherMap.get(klic).getBirthdate());
                pstmt.setDouble(5, teacherMap.get(klic).getSalary());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public void selectAllUcitele(){
        String sql = "SELECT id, jmeno,prijmeni,narozeni,plat FROM ucitele";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("jmeno") + "\t" +
                        rs.getString("prijmeni") + "\t" +
                        rs.getInt("narozeni") + "\t" +
                        rs.getDouble("plat"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void getRecordStudent() throws InputMismatchException{
        System.out.println("Uvedte id studenta, ktereho checete vypsat: ");
        int id = sc.nextInt();

        String sql = "SELECT id, jmeno,prijmeni,narozeni,prumer FROM studenti where id=?";
        try {
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("jmeno") + "\t" +
                        rs.getString("prijmeni") + "\t" +
                        rs.getInt("narozeni") + "\t" +
                        rs.getDouble("prumer"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void getRecordUcitel() throws InputMismatchException{
        System.out.println("Uvedte id ucitele, ktereho checete vypsat: ");
        int id = sc.nextInt();

        String sql = "SELECT id,jmeno,prijmeni,narozeni,plat FROM ucitele where id=?";
        try {
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("jmeno") + "\t" +
                        rs.getString("prijmeni") + "\t" +
                        rs.getInt("narozeni") + "\t" +
                        rs.getDouble("plat"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void deleteStudent() throws  InputMismatchException {
        System.out.println("Uvedte id studenta, ktereho checete smazat: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM studenti WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void deleteUcitel() throws InputMismatchException {
        System.out.println("Uvedte id ucitele, ktereho checete smazat: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM ucitele WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }








}









