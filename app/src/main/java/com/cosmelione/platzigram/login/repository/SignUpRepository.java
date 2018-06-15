package com.cosmelione.platzigram.login.repository;

public interface SignUpRepository {

    void signUp(String email, String password, String name, String username);

}
