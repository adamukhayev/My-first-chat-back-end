package service;

import entity.AddFriendEntity;
import entity.MsgEntity;
import entity.PersonEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPersonService {

    void addPerson(PersonEntity personEntity) throws SQLException;

    PersonEntity loginPerson(PersonEntity personEntity) throws Exception;

    void updatePerson(PersonEntity personEntity, int id) throws SQLException;

    void deletPerson(int id) throws SQLException;

    PersonEntity getPersons(String id) throws SQLException;

    ArrayList<PersonEntity> readPersons() throws SQLException;


    ArrayList<MsgEntity> readMesagge(String name,String accountName) throws SQLException;

    void addMsg(MsgEntity msgEntity) throws Exception;

    void addFriend(AddFriendEntity addFriendEntity);

    AddFriendEntity getBol(String nick1, String nick2) throws SQLException;
}
