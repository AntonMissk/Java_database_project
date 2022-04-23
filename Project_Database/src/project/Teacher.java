package project;


import java.util.*;


public class Teacher extends People {
    private Map<Integer, Student> students;
    private double salary;

    public Teacher(String name, String surname, int id, int birthdate) {
        students = new HashMap<>();

        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthdate = birthdate;
    }


    public int  getPocetStudentu(){
        return students.size();
    }



    public void setStudents(int id, String name, String surname, int birthdate, double prumer)
    {
        students.put(id, new Student(name, surname, id, birthdate, prumer));
    }
    public void setSalary() {
        salary = 0;
        Set<Integer> klice = students.keySet();
        for (Integer klic : klice) {
            salary+=1500;
            if(students.get(klic).getStudijniPrumer()<=Student.MINPRUMER){
                salary+=1000;
            }

                }
            }

    public void trideniStudentu(){
        Set<Map.Entry<Integer,Student>> entrySet = students.entrySet();
        List<Map.Entry<Integer,Student>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Student>>() {
            @Override
            public int compare(Map.Entry<Integer, Student> o1, Map.Entry<Integer, Student> o2) {
                if(o1.getValue().getStudijniPrumer()>o2.getValue().getStudijniPrumer())
                    return 1;
                else if(o1.getValue().getStudijniPrumer()<o2.getValue().getStudijniPrumer())
                    return -1;
                return 0 ;
            }
        });
        System.out.println();
        System.out.println("----------");
        System.out.println("Vypis setrideniho listu: ");
        list.forEach(e ->{
            System.out.println(e.getKey()+"\t"+ e.getValue());

        });
        System.out.println("----------");
        System.out.println();
    }



    @Override
    public String toString(){
        return "ID: "+getId()+" Jmeno: "+ getName()+" Prijmeni: "+ getSurname()+
                "\nrok narozeni: "+ getBirthdate()+ " plat: "+ getSalary()+" ma studntu "+ getPocetStudentu();

    }


    public double getSalary(){
        return salary;
    }

    public void vypis(){
        System.out.println(students);
    }

    public void odstraneniStudenta(int id){
        try {
            students.remove(id);
        }catch (NullPointerException e){
            System.err.println("Neexistuje student s id "+ id);
            e.printStackTrace();
        }
    }


    public boolean jeStudent(int id){
        if (students.containsKey(id)){
            return true;
        }
        return false;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    String getSurname() {
        return surname;
    }

    @Override
    int getId() {
        return id;
    }

    @Override
    int getBirthdate() {
        return birthdate;
    }
}
