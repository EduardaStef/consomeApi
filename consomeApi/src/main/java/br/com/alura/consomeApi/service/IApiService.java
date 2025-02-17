package br.com.alura.consomeApi.service;

public interface IApiService {

    <T> T converterJson(String json, Class<T> classe);
}
