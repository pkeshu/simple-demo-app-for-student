package com.keshar.redditclone.services;

import com.keshar.redditclone.model.entities.RefreshToken;
import com.keshar.redditclone.repository.RefreshTokenRepository;
import com.keshar.redditclone.utils.exception.SpringRedditException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
    }

    @Transactional
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
