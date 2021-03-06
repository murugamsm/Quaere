package org.quaere.jpa;

import org.junit.Test;
import org.jmock.Mockery;
import org.quaere.jpa.model.NamedEntity;
import org.quaere.jpa.model.UnnamedEntity;
import org.quaere.expressions.Identifier;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import junit.framework.Assert;

public class QueryableEntityTest {
    final Mockery context = new Mockery();
    final EntityManager dummyManager = context.mock(EntityManager.class);
    @Test
    public void entityNameIsAliasForNamedEntities() {
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<NamedEntity> queryableEntity = manager.entity(NamedEntity.class);
        Assert.assertEquals("Named", queryableEntity.getEntityName());
    }
    @Test
    public void entityNameIsClassNameForUnnamedEntities() {
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<UnnamedEntity> queryableEntity = manager.entity(UnnamedEntity.class);
        Assert.assertEquals("UnnamedEntity", queryableEntity.getEntityName());
    }
    @Test
    public void identifierIsNotChanged() {
        Identifier identifer = new Identifier("id");
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<UnnamedEntity> queryableEntity = manager.entity(UnnamedEntity.class);
        Assert.assertSame(identifer, queryableEntity.getSourceIdentifier(identifer));

    }

    @Test
    public void annotationIsPresentWithNoName() {
        QueryableEntityManager manager = new QueryableEntityManager(dummyManager);
        QueryableEntity<AnnotatedUnnamedEntity> queryableEntity = manager.entity(AnnotatedUnnamedEntity.class);
        Assert.assertEquals("AnnotatedUnnamedEntity", queryableEntity.getEntityName());

    }

    @Entity
    static class AnnotatedUnnamedEntity {
    }
}
