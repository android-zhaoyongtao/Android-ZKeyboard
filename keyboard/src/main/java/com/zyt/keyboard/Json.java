package com.zyt.keyboard;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhaoyongtao on 2018/3/17.
 */

public class Json {
    private final static Gson gson = new Gson();

    public static Gson getInstance() {
        return gson;
    }


    public static class TypeToken<T> {
        final Class<? super T> rawType;
        final Type type;
        final int hashCode;

        /**
         * Constructs a new type literal. Derives represented class from type
         * parameter.
         * <p/>
         * <p>Clients create an empty anonymous subclass. Doing so embeds the type
         * parameter in the anonymous class's type hierarchy so we can reconstitute it
         * at runtime despite erasure.
         */
        @SuppressWarnings("unchecked")
        protected TypeToken() {
            this.type = getSuperclassTypeParameter(getClass());
            this.rawType = (Class<? super T>) $Gson$Types.getRawType(type);
            this.hashCode = type.hashCode();
        }

        public Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                return null;
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * Gets underlying {@code Type} instance.
         */
        public final Type getType() {
            return type;
        }


        @Override
        public final int hashCode() {
            return this.hashCode;
        }

        @Override
        public final boolean equals(Object o) {
            return o instanceof TypeToken<?> && $Gson$Types.equals(type, ((TypeToken<?>) o).type);
        }

        @Override
        public final String toString() {
            return $Gson$Types.typeToString(type);
        }
    }

}
