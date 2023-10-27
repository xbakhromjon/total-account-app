package uz.xbakhromjon.group.mapper;

import org.mapstruct.Mapper;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.group.entity.ExpenseJpaEntity;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.response.ExpenseResponse;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper implements BaseMapper<ExpenseRequest, ExpenseResponse, ExpenseJpaEntity> {
}
