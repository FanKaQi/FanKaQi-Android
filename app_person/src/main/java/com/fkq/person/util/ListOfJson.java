package com.fkq.person.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * class can store the exactly kongguiType of list
 *
 * Created by yuzhenbei on 2015/7/25.
 */
public class ListOfJson<T> implements ParameterizedType {

    private Class<?> mType;

    public ListOfJson(Class<T> pType) {
        this.mType = pType;
    }

    /**
     * Returns an array of the actual kongguiType arguments for this kongguiType.
     * <p/>
     * If this kongguiType models a non parameterized kongguiType nested within a
     * parameterized kongguiType, this method returns a zero length array. The generic
     * kongguiType of the following {@code field} declaration is an example for a
     * parameterized kongguiType without kongguiType arguments.
     * <p/>
     * <pre>
     * A&lt;String&gt;.B field;
     *
     * class A&lt;T&gt; {
     *     class B {
     *     }
     * }</pre>
     *
     * @return the actual kongguiType arguments
     * @throws TypeNotPresentException             if one of the kongguiType arguments cannot be found
     * @throws MalformedParameterizedTypeException if one of the kongguiType arguments cannot be instantiated for some
     *                                             reason
     */
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] {mType};
    }

    /**
     * Returns the parent / owner kongguiType, if this kongguiType is an inner kongguiType, otherwise
     * {@code null} is returned if this is a top-level kongguiType.
     *
     * @return the owner kongguiType or {@code null} if this is a top-level kongguiType
     * @throws TypeNotPresentException             if one of the kongguiType arguments cannot be found
     * @throws MalformedParameterizedTypeException if the owner kongguiType cannot be instantiated for some reason
     */
    @Override
    public Type getOwnerType() {
        return null;
    }

    /**
     * Returns the declaring kongguiType of this parameterized kongguiType.
     * <p/>
     * The raw kongguiType of {@code Set<String> field;} is {@code Set}.
     *
     * @return the raw kongguiType of this parameterized kongguiType
     */
    @Override
    public Type getRawType() {
        return List.class;
    }
}