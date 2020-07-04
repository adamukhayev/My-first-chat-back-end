package controlers;

import entity.MsgEntity;
import entity.PersonEntity;
import lombok.SneakyThrows;
import mappers.PersonMappers;
import models.AddFriendDto;
import models.MsgDto;
import models.PersonDto;
import service.IPersonService;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet("/")
public class PersonControler extends HttpServlet {


    PersonMappers personMappers = new PersonMappers();

    IPersonService iPersonService = new PersonService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            controlerGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            controlerPost(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controlerPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            controlerDelete(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void controlerGet(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String operation = req.getRequestURI();
        PrintWriter printWriter = resp.getWriter();
        System.out.println(operation);
        switch (operation) {
            case "/login": {
                System.out.println("Djoh zashel");
                    String id = req.getParameter("login");
                    PersonDto personDto = personMappers.mapperEntityToDto(iPersonService.getPersons(id));
                    printWriter.write(personMappers.mapperDtoToJson(personDto));
                    break;

            }
            case "/bol": {
                System.out.println("Djoh zashel");
                String nick1 = req.getParameter("nickName1");
                String nick2 = req.getParameter("nickName2");
                System.out.println(nick1+" "+nick2);
                AddFriendDto addFriendDto = personMappers.mapperAddFriendEntityToAddFriendDto(iPersonService.getBol(nick1,nick2));
                System.out.println("ADDdto " +addFriendDto.getNickName1()+" "+addFriendDto.getNickName2());
                printWriter.write(personMappers.mapperAddFriendDtoToJson(addFriendDto));
                break;

            }
            case "/all": {
                ArrayList<PersonEntity> list = iPersonService.readPersons();
                    printWriter.write(personMappers.mapperListEntityToJson(list));
                break;
            }
            case "/message": {
                String name = req.getParameter("login");
                String accountName = req.getParameter("accountName");
                System.out.println(name);
                ArrayList<MsgEntity> list = iPersonService.readMesagge(name,accountName);
                    printWriter.write(personMappers.mapperListEntityMsgToJson(list));
                printWriter.flush();
                break;
            }

        }
    }

    public void controlerPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uri = req.getRequestURI();
        BufferedReader reader = null;
        try {
            reader = req.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(uri);
        switch (uri) {
            case "/add": {
                PersonDto personDto = personMappers.readerToDto(reader);
                iPersonService.addPerson(personMappers.mapperDtoToEntity(personDto));
                break;
            }
            case "/friend": {
                AddFriendDto addFriendDto = personMappers.readerToAddFriendDto(reader);
                iPersonService.addFriend(personMappers.mapperAddFriendDtoToAddFriendEntity(addFriendDto));
                break;
            }
            case "/login": {
                PersonDto personDto = personMappers.readerToDto(reader);
                try {
                    PrintWriter printWriter = resp.getWriter();
                    PersonEntity personEntity = iPersonService.loginPerson(personMappers.mapperDtoToEntity(personDto));
                    PersonDto dto = personMappers.mapperEntityToDto(personEntity);
                    String json = personMappers.mapperDtoToJson(dto);
                    printWriter.write(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } case "/addMsg": {
                MsgDto msgDto = personMappers.readerToMsgDto(reader);
                iPersonService.addMsg(personMappers.mapperMsgDtoToMsgEntity(msgDto));
                break;
            }     case "/update": {

                PersonDto personDto = personMappers.readerToDto(reader);
                int id = personDto.getPerson_id();
                iPersonService.updatePerson(personMappers.mapperDtoToEntity(personDto), id);
                break;
            }

        }
    }

    public void controlerPut(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//        String option = req.getRequestURI();
//        System.out.println("OPTION "+option);
//        switch (option) {
//            case "/update": {
//                String json = req.getQueryString();
//                PersonDto dto = personMappers.mapperJsonToDto(json);
//                int id = dto.getPerson_id();
//                iPersonService.updatePerson(personMappers.mapperDtoToEntity(dto), id);
//                break;
//            }
//        }
    }

    public void controlerDelete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String option = req.getRequestURI();
        System.out.println("Work : "+option);
        switch (option) {
            case "/delete": {
                System.out.println("Work2 : "+option);
                if (Objects.equals(req.getParameter("id"), null)) {
                    throw new Exception("Not found param id");
                }
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println("ID = "+id);
                iPersonService.deletPerson(id);
                break;
            }
        }
    }
}
