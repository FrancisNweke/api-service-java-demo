package com.asix.demo.apidata;


import org.hibernate.HibernateException;
import org.hibernate.boot.*;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;


public class CustomUniqueValueGenerator  {

    public CustomUniqueValueGenerator() {
    }

    public String generateStudentID(String prefix, Long lastAllocatedNumber, boolean useSeparator){
       String generatedStudentId = null;

        if(prefix.isBlank()){
            var studentIdWithoutPrefix = lastAllocatedNumber + 1L;

            generatedStudentId = ((Long)studentIdWithoutPrefix).toString();
        } else if(!prefix.isBlank() && useSeparator == true){
            var studentIdWithPrefix = lastAllocatedNumber + 1L;

            var suffix = ((Long)studentIdWithPrefix).toString();

            generatedStudentId = String.format("%s-%s", prefix,suffix);
        } else{
            var studentIdWithPrefix = lastAllocatedNumber + 1L;

            var suffix = ((Long)studentIdWithPrefix).toString();

            generatedStudentId = prefix.toUpperCase() + suffix;
        }

        return generatedStudentId;
    }



    //region Experimental Code
    /*implements IdentifierGenerator, Configurable

    private String prefix;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        var ids = session.createQuery(query).stream();

        Long max = ids //.map(o -> (prefix + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefix + "-" + (max + 1);
    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }*/
    //endregion

}
