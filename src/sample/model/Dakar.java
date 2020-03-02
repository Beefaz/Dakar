package sample.model;

public class Dakar {
    private int dakarID;
    private String teamName;
    private String nameSurname;
    private String sponsors;
    private String racingCars;
    private int members;

    public Dakar(int dakarID, String teamName, String nameSurname, String sponsors, String racingCars, int members) {
        this.dakarID = dakarID;
        this.teamName = teamName;
        this.nameSurname = nameSurname;
        this.sponsors = sponsors;
        this.racingCars = racingCars;
        this.members = members;
    }

    //registracijai
    public Dakar(String teamName, String nameSurname, String sponsors, String racingCars, int members) {
        this.teamName = teamName;
        this.nameSurname = nameSurname;
        this.sponsors = sponsors;
        this.racingCars = racingCars;
        this.members = members;
    }

    public Dakar() {
    }

    public int getDakarID() {
        return dakarID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getSponsors() {
        return sponsors;
    }

    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

    public String getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(String racingCars) {
        this.racingCars = racingCars;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Dakar{" +
                "dakarID=" + dakarID +
                ", teamName='" + teamName + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", sponsors='" + sponsors + '\'' +
                ", racingCars='" + racingCars + '\'' +
                ", members=" + members +
                '}';
    }
}