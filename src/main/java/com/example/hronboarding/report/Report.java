package com.example.hronboarding.report;

public record Report(String title, String content) {
    @Override
    public String toString() {
        return title + System.lineSeparator() + content;
    }
}
