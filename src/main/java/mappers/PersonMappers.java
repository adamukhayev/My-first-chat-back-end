package mappers;

import com.google.gson.Gson;
import entity.AddFriendEntity;
import entity.MsgEntity;
import entity.PersonEntity;
import models.AddFriendDto;
import models.MsgDto;
import models.PersonDto;
import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonMappers {
    Gson gson = new Gson();
    ModelMapper mapper = new ModelMapper();

    public PersonDto mapperJsonToDto(String json) {
        return gson.fromJson(json, PersonDto.class);
    }

    public PersonEntity mapperDtoToEntity(PersonDto dto) {
        String json = gson.toJson(dto);
        return gson.fromJson(json, PersonEntity.class);
    }

    public String mapperListEntityToJson(ArrayList<PersonEntity> entities) {
        List<PersonDto> dtos = Arrays
                .asList(mapper.map(entities, PersonDto[].class));
        String json = gson.toJson(dtos);
        return json;
    }

    public PersonDto mapperEntityToDto(PersonEntity entity) {
        String json = gson.toJson(entity);
        return gson.fromJson(json, PersonDto.class);
    }

    public String mapperDtoToJson(PersonDto dto) {
        return gson.toJson(dto);
    }

    public String mapperListToJson(List<PersonEntity> entityList) {
        return gson.toJson(entityList);
    }

    public PersonDto readerToDto(BufferedReader reader) {
        return gson.fromJson(reader, PersonDto.class);
    }

    public String mapperMsgDtoToJson(MsgDto msgDto){
        return gson.toJson(msgDto);
    }

    public MsgDto mapperJsonToMsgDto(String json){ /// Dto Работа между клиентом и бекЭндом
        return  gson.fromJson(json, MsgDto.class);
    }

    public MsgEntity mapperMsgDtoToMsgEntity(MsgDto msgDto){
        String json = gson.toJson(msgDto);
        return gson.fromJson(json, MsgEntity.class);
    }
    public MsgDto mapperMsgEntityToMsgDto(MsgEntity msgEntity){ // Entity  работа между бекЭндом и Базой данных
        String json = gson.toJson(msgEntity);
        return gson.fromJson(json,MsgDto.class);
    }

    public MsgDto readerToMsgDto(BufferedReader reader) {
        return gson.fromJson(reader, MsgDto.class);
    }

    public String mapperListEntityMsgToJson(ArrayList<MsgEntity> entities) {
        List<MsgDto> dtos = Arrays
                .asList(mapper.map(entities, MsgDto[].class));
        String json = gson.toJson(dtos);
        return json;
    }


    public AddFriendDto mapperJsonToAddFriendDto(String json) {
        return gson.fromJson(json, AddFriendDto.class);
    }

    public AddFriendEntity mapperAddFriendDtoToAddFriendEntity(AddFriendDto addFriendDto) {
        String json = gson.toJson(addFriendDto);
        return gson.fromJson(json, AddFriendEntity.class);
    }

    public AddFriendDto mapperAddFriendEntityToAddFriendDto(AddFriendEntity addFriendEntity) {
        String json = gson.toJson(addFriendEntity);
        return gson.fromJson(json, AddFriendDto.class);
    }

    public AddFriendDto readerToAddFriendDto(BufferedReader reader) {
        return gson.fromJson(reader, AddFriendDto.class);
    }

    public String mapperAddFriendDtoToJson(AddFriendDto addFriendDto) {
        return gson.toJson(addFriendDto);
    }
    }

