package uz.xbakhromjon.common;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<REQ, RES, E> {
    E toEntity(REQ source);

    RES toResponse(E source);

    List<RES> toResponse(Collection<E> sourceCollection);
}
