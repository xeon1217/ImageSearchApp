package com.example.imagesearchapp.data.repository.error

import java.lang.Exception

sealed class RepositoryException(message: String) : Exception(message) {

    //일반적인 오류
    //주로 API에 필요한 필수 파라미터와 관련하여 서버가 클라이언트 오류를 감지해 요청을 처리하지 못한 상태입니다.
    class WrongRequestException(message: String) : RepositoryException(message)

    //인증 오류(주로 토큰 관련)
    //해당 리소스에 유효한 인증 자격 증명이 없어 요청에 실패한 상태입니다.
    class AuthenticationException(message: String): RepositoryException(message)

    //권한 오류
    //서버에 요청이 전달되었지만, 권한 때문에 거절된 상태입니다.
    class PermissionException(message: String): RepositoryException(message)
    
    //네트워크가 연결되지 않은 오류
    class NetworkNotConnectingException(message: String): RepositoryException(message)

    //네트워크 관련 알 수 없는 오류
    class UnknownNetworkException(message: String): RepositoryException(message)

    // 서버 시스템 오류
    class ServerSystemException(message: String): RepositoryException(message)

    //알 수 없는 오류
    class UnknownException(message: String): RepositoryException(message)

}
