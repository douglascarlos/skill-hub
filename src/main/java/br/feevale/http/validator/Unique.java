package br.feevale.http.validator;

public interface Unique {
    public boolean unique(String value, String column);
    public boolean unique(String value, String column, long exceptId);
}
