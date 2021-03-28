package com.example.imagesearchapp.data.remote.imageSearch.request

import com.example.imagesearchapp.data.repository.error.RepositoryException
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * https://developers.kakao.com/docs/latest/ko/reference/rest-api-reference
 * 카카오 API 응답 관련 참고 사이트
 */
object KakaoImageSearchAPIExceptionConverter {
    fun toRepositoryException(originException: Throwable): RepositoryException {
        val message: String = originException.message ?: ""

        return when (originException) {
            is HttpException -> {
                when (originException.code()) {
                    400 -> {
                        RepositoryException.WrongRequestException("$message, Bad Request Error")
                    }
                    401 -> {
                        RepositoryException.AuthenticationException("$message, Unauthorized Error")
                    }
                    403 -> {
                        RepositoryException.PermissionException("$message, Forbidden Error")
                    }
                    500, 502 -> {
                        RepositoryException.ServerSystemException("$message, Internal Server Error or Bad Gateway Error")
                    }
                    503 -> {
                        RepositoryException.ServerSystemException("$message, Service Unavailable Error")
                    }
                    else -> {
                        RepositoryException.UnknownNetworkException("$message, Unknown Network Error")
                    }
                }
            }
            is UnknownHostException -> {
                RepositoryException.NetworkNotConnectingException("$message, Network Not Connecting Error")
            }
            else -> {
                RepositoryException.UnknownException("$message, Network Unknown Error")
            }
        }
    }
}