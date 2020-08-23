package com.xsn.convert;

import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestConverterFactory {

    class Sup {
        public Sup(Integer i) {
            if (i == 1) {
                new Sub1(i);
            }

            if (i == 2) {
                new Sub2(i);
            }
        }
    }

    class Sub1 extends Sup {
        public Sub1(Integer i) {
            super(0);
            System.out.println("sub1" + i);
        }
    }

    class Sub2 extends Sup {
        public Sub2(Integer i) {
            super(0);
            System.out.println("sub2" + i);
        }
    }

    class MyCnverterFactory implements ConverterFactory<Integer, Sup>, ConditionalConverter {

        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            return true;
        }

        @Override
        public <T extends Sup> Converter<Integer, T> getConverter(Class<T> targetType) {
            return (Converter<Integer, T>) (Integer i) -> {
                return (T) new Sup(i);
            };
        }
    }

    @Test
    public void test1() {
        new MyCnverterFactory().getConverter(Sub1.class).convert(1);
        new MyCnverterFactory().getConverter(Sub1.class).convert(2);
    }

    @Test
    public void test2() {
        DefaultConversionService defaultConversionService
                = new DefaultConversionService();

        defaultConversionService.addConverter(new ConditionalGenericConverter() {
            @Override
            public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
                return targetType.getType() == Sup.class;
            }

            @Override
            public Set<ConvertiblePair> getConvertibleTypes() {
                return new HashSet<ConvertiblePair>() {
                    {
                        add(new ConvertiblePair(Sub1.class, Sup.class));
                        add(new ConvertiblePair(Sub2.class, Sup.class));
                    }
                };
            }

            @Override
            public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
                return (Sup) source;
            }
        });

        System.out.println(defaultConversionService.convert(new Sub1(1), Sup.class));
    }
}
