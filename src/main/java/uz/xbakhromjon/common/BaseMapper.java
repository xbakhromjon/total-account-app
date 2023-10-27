package uz.xbakhromjon.common;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<REQ, RES, E> {
    E toEntity(REQ source);

    RES toResponse(E source);

    List<RES> toResponse(Collection<E> sourceCollection);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialMap(@MappingTarget E target, REQ source);
}
