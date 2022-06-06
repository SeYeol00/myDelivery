package com.sparta.mydelivery.model;

public enum UserRoleEnum {
    //enum은 유저와 어드민 두 개의 값으로 한정 짓기 위해 만들어줌
    //상수 열거형, 지역변수

    CONSUMER(Authority.CONSUMER), // 사용자 권한
    RESTAURANT_OWNER(Authority.RESTAURANT_OWNER); // 관리자 권한

    private final String authority;


    UserRoleEnum(String authority){
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority{//이너 클래스
        public static final String CONSUMER = "ROLE_CONSUMER";
        public static final String RESTAURANT_OWNER = "ROLE_RESTAURANT_OWNER";
    }
}
