package project;


public class Student extends People {

    static double MINPRUMER= 2.0;
    private double studijniPrumer;
    private double stipendia=0;
    private double znamka;
    private int iter= 0;



    public Student(String name, String surname, int id, int birthdate, double studijniPrumer){
        this.name=name;
        this.surname=surname;
        this.id=id;
        this.birthdate=birthdate;
        this.studijniPrumer=studijniPrumer;

    }

    @Override
    public String toString(){
        return "ID: "+getId()+" Jmeno: "+ getName()+" Prijmeni: "+ getSurname()+
                "\nrok narozeni: "+ getBirthdate()+ " prumer: "+ getStudijniPrumer();
    }

    public void setZnamka(double znamka){
        this.znamka+=znamka;
        this.iter+=1;
    }

    public double getZnamka(){
        return znamka;
    }

    public void vypocetPrumeru(){
        studijniPrumer = getZnamka()/iter;
    }

    public void vypocetStipendia(){
        stipendia=0;
        if (getStudijniPrumer()<=MINPRUMER){
            stipendia+=1500;
        }
    }

    public double getStipendia(){
        return stipendia;
    }

    public double getStudijniPrumer(){
        return studijniPrumer;
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
