package com.keshar.redditclone.utils;

import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.spi.Configurable;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Map;
import java.util.stream.Stream;

public class IdGenerator implements IdentifierGenerator, Configurable {
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    private String gen_id;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
//        String query = String.format("select %s from %s",
//                session.getEntityPersister(obj.getClass().getName(), obj)
//                        .getIdentifierPropertyName(),
//                obj.getClass().getSimpleName());
//        Stream ids = session.createQuery(query).stream();
        return gen_id;
    }

    @Override
    public void configure(Map map) {

    }
}
