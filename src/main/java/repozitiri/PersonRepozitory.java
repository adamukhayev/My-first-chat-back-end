package repozitiri;

import conection.ConnectionJDBC;
import entity.AddFriendEntity;
import entity.MsgEntity;
import entity.PersonEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class PersonRepozitory {
    ConnectionJDBC jdbc = new ConnectionJDBC();

    public void addPerson(PersonEntity personEntity) throws Exception {
        Connection connection = jdbc.getConnection();
        Date date = new Date();
        try {
            String str = "INSERT INTO questionnairetable (firstName,lastName,age,email,phone,login,password,url,date) values (?,?,?,?,?,?,?,?,?)";
            System.out.println(personEntity.getName());
            String dateLine = date.toString();
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, personEntity.getName());
            preparedStatement.setString(2, personEntity.getSurname());
            preparedStatement.setInt(3, personEntity.getAge());
            preparedStatement.setString(4, personEntity.getEmail());
            preparedStatement.setString(5, personEntity.getPhone());
            preparedStatement.setString(6, personEntity.getLogin());
            preparedStatement.setString(7, personEntity.getPassword());
            preparedStatement.setString(8, personEntity.getUrl());
            preparedStatement.setString(9, dateLine);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
        }
        throw new Exception("ERROR");
    }

    public void updatePerson(PersonEntity personEntity, int id) throws SQLException {
        Connection connection = jdbc.getConnection();

        String str = "UPDATE questionnairetable SET firstname =?, lastname = ?,age = ?,email = ?,phone = ?,login = ? where person_id = ?";

        PreparedStatement statement = connection.prepareStatement(str);

        statement.setString(1, personEntity.getName());

        statement.setString(2, personEntity.getSurname());
        statement.setInt(3, personEntity.getAge());
        statement.setString(4, personEntity.getEmail());
        statement.setString(5, personEntity.getPhone());
        statement.setString(6, personEntity.getLogin());

        statement.setInt(7, id);

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deletePerson(int id) throws SQLException {
        Connection connection = jdbc.getConnection();
        String str = "DELETE FROM friend WHERE friend_id = ?";
        PreparedStatement statement = connection.prepareStatement(str);

        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public PersonEntity getPerson(String id) throws SQLException {
        PersonEntity personEntity = new PersonEntity();
        Connection connection = jdbc.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM questionnairetable WHERE login ='" + id+"'");
            if (resultSet.next()) {
                personEntity.setName(resultSet.getString("firstname"));
                personEntity.setSurname(resultSet.getString("lastname"));
                personEntity.setAge(resultSet.getInt("age"));
                personEntity.setEmail(resultSet.getString("email"));
                personEntity.setPhone(resultSet.getString("phone"));
                personEntity.setLogin(resultSet.getString("login"));
                personEntity.setDate(resultSet.getString("date"));
                statement.close();
                connection.close();
                return personEntity;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        throw new SQLException("Its entity by id not found!");
    }

    public ArrayList getPersons() throws SQLException {
        ArrayList<PersonEntity> list = new ArrayList<>();

        Connection connection = jdbc.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM questionnairetable");
        while (resultSet.next()) {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setPerson_id(resultSet.getInt("person_id"));
            personEntity.setName(resultSet.getString("firstname"));
            personEntity.setSurname(resultSet.getString("lastname"));
            personEntity.setAge(resultSet.getInt("age"));
            personEntity.setEmail(resultSet.getString("email"));
            personEntity.setPhone(resultSet.getString("phone"));
            personEntity.setLogin(resultSet.getString("login"));
            personEntity.setDate(resultSet.getString("date"));
            list.add(personEntity);
        }
        if (list.size() > 0) {
            statement.close();
            connection.close();
            return list;
        }
        throw new SQLException("ERROR");
    }

    public PersonEntity loginPerson(PersonEntity personEntity) throws Exception {
        Connection connection = jdbc.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login FROM questionnairetable where login='" + personEntity.getLogin() + "'and password='" + personEntity.getPassword() + "'");
            if (resultSet.next()) {
                personEntity.setLogin(resultSet.getString(1));
                statement.close();
                connection.close();
                return personEntity;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

            throw new Exception("ERROR");

    }

    public ArrayList<MsgEntity> getMessage(String name,String accountName) throws SQLException {
        ArrayList<MsgEntity> list = new ArrayList<>();
        Connection connection = jdbc.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT m.sms FROM questionnairetable\n" +
                    "left join messages m on questionnairetable.login = m.person_key\n" +
                    "where m.person_login = '" + accountName+"' and m.person_key = '"+name+"' order by m.messag_id");
            while (resultSet.next()) {
                MsgEntity msgEntity = new MsgEntity();
                msgEntity.setMsg(resultSet.getString("sms"));
                list.add(msgEntity);
            }
        statement.close();
        connection.close();
            return list;

    }

    public void addMsg(MsgEntity msgEntity) throws Exception {
        Connection connection = jdbc.getConnection();
        Date date = new Date();
        try {
            String str = "INSERT INTO messages (person_login,sms,person_key) values (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, msgEntity.getAccountName());
            preparedStatement.setString(2, msgEntity.getMsg());
            preparedStatement.setString(3, msgEntity.getName());

            preparedStatement.executeUpdate();

            String str1 = "INSERT INTO messages (person_login,sms,person_key) values (?,?,?)";

             preparedStatement = connection.prepareStatement(str1);
            preparedStatement.setString(1,msgEntity.getName() );
            preparedStatement.setString(2, msgEntity.getMsg());
            preparedStatement.setString(3, msgEntity.getAccountName());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
        }
        throw new Exception("ERROR");


    }

    public void addFriend(AddFriendEntity addFriendEntity) throws Exception {
        Connection connection = jdbc.getConnection();
        try {
            String str = "INSERT INTO friend (person_login,friend_login) values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, addFriendEntity.getNickName1());
            preparedStatement.setString(2, addFriendEntity.getNickName2());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
        }
        throw new Exception("ERROR");
    }

    public AddFriendEntity getBol(String nick1, String nick2) throws SQLException {
        AddFriendEntity addFriendEntity = new AddFriendEntity();
        Connection connection = jdbc.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM friend where person_login = '"+nick1+"' and friend_login = '"+nick2+"';" );
            if (resultSet.next()) {
                addFriendEntity.setId(resultSet.getInt("friend_id"));
                addFriendEntity.setNickName1(resultSet.getString("person_login"));
                addFriendEntity.setNickName2(resultSet.getString("friend_login"));
                statement.close();
                connection.close();
                return addFriendEntity;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        throw new SQLException("Its entity by id not found!");
    }
}
