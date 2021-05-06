package com.luminor.test.repository;

import com.luminor.test.model.Payment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Repository
//public interface PaymentRepository extends JpaRepository<Payment, UUID> {
//
//}

@Repository
public class PaymentRepository implements JpaRepository<Payment, UUID> {

    private Map<UUID, Payment> fakeDB = new HashMap<>();

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(fakeDB.values());
    }

    @Override
    public List<Payment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Payment> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Payment entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Payment> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Payment> S save(S entity) {
        fakeDB.put(entity.getUuid(), entity);
        return entity;
    }

    @Override
    public <S extends Payment> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Payment> findById(UUID uuid) {
        return Optional.of(fakeDB.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Payment> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Payment> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Payment getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Payment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Payment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Payment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Payment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Payment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Payment> boolean exists(Example<S> example) {
        return false;
    }
}