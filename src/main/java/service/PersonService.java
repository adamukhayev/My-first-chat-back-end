package service;

import entity.AddFriendEntity;
import entity.MsgEntity;
import entity.PersonEntity;
import repozitiri.PersonRepozitory;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonService implements IPersonService {


    PersonRepozitory repozitory = new PersonRepozitory();

    @Override
    public void addPerson(PersonEntity personEntity) throws SQLException {
        try {
            repozitory.addPerson(personEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFriend(AddFriendEntity addFriendEntity)  {
        try {
            repozitory.addFriend(addFriendEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AddFriendEntity getBol(String nick1, String nick2) throws SQLException {
        return repozitory.getBol(nick1,nick2);
    }

    @Override
    public PersonEntity loginPerson(PersonEntity personEntity) throws Exception {
           return repozitory.loginPerson(personEntity);
    }

    @Override
    public void updatePerson(PersonEntity personEntity, int id) throws SQLException {
       repozitory.updatePerson(personEntity,id);
    }

    @Override
    public void deletPerson(int id) throws SQLException {
       repozitory.deletePerson(id);
    }

    @Override
    public PersonEntity getPersons(String id) throws SQLException {
       return repozitory.getPerson(id);

    }

    @Override
    public ArrayList readPersons() throws SQLException {
        ArrayList<PersonEntity> list = repozitory.getPersons();
       return list;
    }

    @Override
    public ArrayList<MsgEntity> readMesagge(String name,String accountName) throws SQLException {
        ArrayList<MsgEntity> list = repozitory.getMessage(name,accountName);
        return list;
    }

    @Override
    public void addMsg(MsgEntity msgEntity) throws Exception {
        repozitory.addMsg(msgEntity);
    }
}
