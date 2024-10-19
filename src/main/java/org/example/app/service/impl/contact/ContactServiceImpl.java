package org.example.app.service.impl.contact;

import org.example.app.dto.contact.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.repository.impl.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// https://docs.spring.io/spring-framework/reference/data-access/transaction.html
// https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;

    @Override
    @Transactional
    public boolean create(ContactDtoRequest request) {
        return repository.create(request);
    }

    @Override
    @Transactional
    public List<Contact> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Contact fetchById(Long id) {
        return repository.fetchById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, ContactDtoRequest request) {
        return repository.update(id, request);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Contact> optional = repository.fetchById(id);
        if (optional.isPresent())
            return repository.delete(id);
        else return false;
    }
}
