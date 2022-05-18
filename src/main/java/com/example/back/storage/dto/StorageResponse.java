package com.example.back.storage.dto;

import com.example.back.board.domain.Board;
import com.example.back.storage.domain.Storage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StorageResponse {
    private Long id;
    private String writer;

    @Builder
    public StorageResponse(Long id, String writer) {
        this.id = id;
        this.writer = writer;
    }

    public static StorageResponse of(Storage storage) {
        return StorageResponse.builder()
                .id(storage.getWorkbook().getId())
                .writer(storage.getUser().getName())
                .build();
    }
    public static List<StorageResponse> listOf(List<Storage> storages) {
        List<StorageResponse> storageResponses = new ArrayList<>();

        for (Storage storage : storages) {
            storageResponses.add(of(storage));
        }

        return storageResponses;
    }
}