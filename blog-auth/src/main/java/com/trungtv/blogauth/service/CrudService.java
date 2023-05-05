package com.trungtv.blogauth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CrudService<E,P>{
    E create(E entity);

    E update(E entity);

    Page<E> list(Pageable pageable);

    Page<E> search(Specification<E> specification, Pageable pageable);

    E get(P id);

    void delete(P id);
}
