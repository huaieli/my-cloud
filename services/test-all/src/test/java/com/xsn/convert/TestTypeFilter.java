package com.xsn.convert;

import org.junit.Test;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

public class TestTypeFilter {

    class A {

    }

    @Service
    class B extends A {

    }

    @Test
    public void test() throws IOException {
        TypeFilter classTypeFilter =
                new AssignableTypeFilter(A.class);

        TypeFilter annotationTypeFilter =
                new AnnotationTypeFilter(Component.class);

        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader("com.xsn.convert.TestTypeFilter.B");
        System.out.println(classTypeFilter.match(metadataReader, metadataReaderFactory));
        System.out.println(annotationTypeFilter.match(metadataReader, metadataReaderFactory));

    }
}
