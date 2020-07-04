package mappers;

import entity.AbstractEntity;
import models.AbstractDto;

public interface Mapper<D extends AbstractDto, E extends AbstractEntity> {

    D mapperJsonToDto(String json);
    E mapperDtoToEntity(D dto);
    D mapperEntityToDto(E entity);
    String mapperDtoToJson(D dto);

}

