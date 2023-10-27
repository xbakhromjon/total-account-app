package uz.xbakhromjon.group.service;

import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.request.PersonRequest;

import java.util.List;

public interface PersonService {
    PersonJpaEntity create(Long groupId, PersonRequest request);

    void getOne(Long id);

    void update(Long id, PersonRequest request);

    void deleteOne(Long id);

    void addMoney(Long personId, float amount);

    List<PersonJpaEntity> getAllByGroupId(Long groupId);

    boolean existsByIdAndGroupId(Long personId, Long groupId);
}
