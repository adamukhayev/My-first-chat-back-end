package entity;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PersonEntity extends AbstractEntity {

    private Integer person_id;

    private String name;

    private String surname;

    private Integer age;

    private String email;

    private String phone;

    private String login;

    private String password;

    private String url;

    private String date;

//    public int getPerson_id() {
//        return person_id;
//    }
//
//    public void setPerson_id(int person_id) {
//        this.person_id = person_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    @Override
//    public String toString() {
//        return "PersonEntity{" +
//                "id=" + person_id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", age=" + age +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", url='" + url + '\'' +
//                ", date='" + date + '\'' +
//                '}';
//    }
}
