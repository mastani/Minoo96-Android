package ir.minoo96.Items;

public class Candidate {
    private int id;
    private String name, profileName, fatherName, Hezb, Tahsilat, Reshteh, Bio, Idea, Savabegh, Others, Image, TChannel, RegisterDate;
    private int Age, Gender, ReCandidate;

    public Candidate() {
        id = 0;
        name = "";
        profileName = "";
        fatherName = "";
        Hezb = "";
        Tahsilat = "";
        Reshteh = "";
        Bio = "";
        Idea = "";
        Savabegh = "";
        Others = "";
        Image = "";
        TChannel = "";
        RegisterDate = "";
        Age = 0;
        Gender = 0;
        ReCandidate = 0;
    }

    public Candidate(int id, String name, String profileName, String fatherName, String hezb, String tahsilat, String reshteh, String bio, String idea, String savabegh, String others, String image, String TChannel, String registerDate, int age, int gender, int reCandidate) {
        this.id = id;
        this.name = name;
        this.profileName = profileName;
        this.fatherName = fatherName;
        Hezb = hezb;
        Tahsilat = tahsilat;
        Reshteh = reshteh;
        Bio = bio;
        Idea = idea;
        Savabegh = savabegh;
        Others = others;
        Image = image;
        this.TChannel = TChannel;
        RegisterDate = registerDate;
        Age = age;
        Gender = gender;
        ReCandidate = reCandidate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getHezb() {
        return Hezb;
    }

    public void setHezb(String hezb) {
        Hezb = hezb;
    }

    public String getTahsilat() {
        return Tahsilat;
    }

    public void setTahsilat(String tahsilat) {
        Tahsilat = tahsilat;
    }

    public String getReshteh() {
        return Reshteh;
    }

    public void setReshteh(String reshteh) {
        Reshteh = reshteh;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getIdea() {
        return Idea;
    }

    public void setIdea(String idea) {
        Idea = idea;
    }

    public String getSavabegh() {
        return Savabegh;
    }

    public void setSavabegh(String savabegh) {
        Savabegh = savabegh;
    }

    public String getOthers() {
        return Others;
    }

    public void setOthers(String others) {
        Others = others;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTChannel() {
        return TChannel;
    }

    public void setTChannel(String TChannel) {
        this.TChannel = TChannel;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getReCandidate() {
        return ReCandidate;
    }

    public void setReCandidate(int reCandidate) {
        ReCandidate = reCandidate;
    }
}