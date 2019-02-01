package com.github.entrypointkr.junlib.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by JunHyeong Lim on 2019-01-05
 */
public class ClassEx {
    private final Class aClass;

    private ClassEx(Class aClass) {
        this.aClass = aClass;
    }

    public static ClassEx of(Class aClass) {
        return new ClassEx(aClass);
    }

    public static Optional<ClassEx> ofName(String name) {
        try {
            return Optional.of(of(Class.forName(name)));
        } catch (ClassNotFoundException e) {
            // Ignore
        }
        return Optional.empty();
    }

    public Set<Field> getAllFields() {
        Set<Field> fields = new HashSet<>();
        Collections.addAll(fields, aClass.getFields());
        Collections.addAll(fields, aClass.getDeclaredFields());
        return fields;
    }

    public Set<Method> getAllMethods() {
        Set<Method> methods = new HashSet<>();
        Collections.addAll(methods, aClass.getMethods());
        Collections.addAll(methods, aClass.getDeclaredMethods());
        return methods;
    }

    public Optional<FieldEx> findField(String fieldName) {
        for (Field field : getAllFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return Optional.of(FieldEx.of(field));
            }
        }
        return Optional.empty();
    }
}
