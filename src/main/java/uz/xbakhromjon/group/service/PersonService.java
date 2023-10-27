package uz.xbakhromjon.group.service;

import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.request.PersonRequest;

public interface PersonService {
    PersonJpaEntity create(PersonRequest request);

    void getOne(Long id);

    PersonJpaEntity update(Long id, PersonRequest request);

    void deleteOne(Long id);
}
