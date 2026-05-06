package com.turkcell.spring_cqrs.application.features.category.query.getall;

import com.turkcell.spring_cqrs.core.mediator.cqrs.Query;
import org.springframework.data.domain.Page;


public record GetAllCategoriesQuery(int pageNumber, int pageSize) implements Query<Page<GetAllCategoriesResponse>> {


}
