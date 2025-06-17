package com.example.servingwebcontent.models;

public interface Selector {
        boolean end();
        Object current();
        void next();
}
