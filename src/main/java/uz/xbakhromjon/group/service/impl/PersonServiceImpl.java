package uz.xbakhromjon.group.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.xbakhromjon.common.constants.ErrorMessages;
import uz.xbakhromjon.common.constants.ResourceNames;
import uz.xbakhromjon.common.exception.ResourceNotFoundException;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.mapper.PersonMapper;
import uz.xbakhromjon.group.repository.PersonRepository;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.service.PersonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonJpaEntity create(Long groupId, PersonRequest request) {
        // map request to entity
        PersonJpaEntity entity = personMapper.toEntity(request);

        // set groupId
        entity.setGroup(GroupJpaEntity.builder().id(groupId).build());

        // save entity
        entity = personRepository.save(entity);
        return entity;
    }

    @Override
    public void getOne(Long id) {

    }

    @Override
    public void update(Long id, PersonRequest request) {
        // find person by id
        PersonJpaEntity entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.PERSON)));
        // partial map request
        personMapper.partialMap(entity, request);
        // save entity
        personRepository.save(entity);
    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public void addMoney(Long personId, float amount) {
        personRepository.addMoney(personId, amount);
    }

    @Override
    public List<PersonJpaEntity> getAllByGroupId(Long groupId) {
        return personRepository.findAllByGroupId(groupId);
    }

    @Override
    public boolean existsByIdAndGroupId(Long personId, Long groupId) {
        return personRepository.existsByIdAndGroupId(personId, groupId);
    }
}
