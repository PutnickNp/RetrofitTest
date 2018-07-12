package rs.tafilovic.retrofittest.rest;

public interface RestCallback<T, E> {
    void onResult(T result);

    void onError(E error);
}
