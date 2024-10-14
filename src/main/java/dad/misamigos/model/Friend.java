package dad.misamigos.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Friend {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final ObjectProperty<Image> photo = new SimpleObjectProperty<>();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Image getPhoto() {
        return photo.get();
    }

    public ObjectProperty<Image> photoProperty() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo.set(photo);
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }

}
