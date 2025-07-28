package DTO;

public class Donor {
    private int donorId;
    private String firstName;
    private String lastName;
    private String telephone;

    //Constructor
    public Donor(int donorId, String firstName, String lastName, String telephone) {
        this.donorId = donorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    //Getters
    public String getTelephone() {
        return telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getDonorId() {
        return donorId;
    }

    //Setters
    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //toString
    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
