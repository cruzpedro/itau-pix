package com.itau.pix.resource.pixkey;

import com.itau.pix.dto.PixKeyDTO;
import com.itau.pix.resource.pixkey.mapper.PixKeyDTOMapper;
import com.itau.pix.resource.pixkey.request.PixKeyCreateRequest;
import com.itau.pix.resource.pixkey.request.PixKeyGetRequest;
import com.itau.pix.resource.pixkey.request.PixKeyUpdateRequest;
import com.itau.pix.resource.pixkey.response.PixKeyResponse;
import com.itau.pix.resource.pixkey.response.PixKeyUpdateResponse;
import com.itau.pix.service.PixKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pix-key")
public class PixKeyResource {

    private final PixKeyDTOMapper pixKeyDTOMapper;
    private final PixKeyService pixKeyService;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody PixKeyCreateRequest request) {
        log.info("C={}, method=create, request={}", getClass().getSimpleName(), request);

        final PixKeyDTO pixKeyDTO = pixKeyDTOMapper.toDto(request);
        final UUID id = pixKeyService.create(pixKeyDTO);

        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<PixKeyUpdateResponse> update(@RequestBody PixKeyUpdateRequest request) {
        log.info("C={}, method=update, request={}", getClass().getSimpleName(), request);

        final PixKeyDTO pixKeyDTO = pixKeyDTOMapper.toDto(request);
        final PixKeyUpdateResponse response = pixKeyDTOMapper.toUpdateResponse(pixKeyService.update(pixKeyDTO));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PixKeyResponse> findById(@PathVariable("id") UUID id) {
        log.info("C={}, method=findById, id={}", getClass().getSimpleName(), id);

        final PixKeyResponse userResponse = pixKeyDTOMapper.toResponse(pixKeyService.findById(id));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    private ResponseEntity<List<PixKeyResponse>> findBy(PixKeyGetRequest request) {
        log.info("C={}, method=findBy, pixKeyGetRequest={}", getClass().getSimpleName(), request);

        final PixKeyDTO pixKeyDTO = pixKeyDTOMapper.toDto(request);
        final List<PixKeyResponse> userResponse = pixKeyDTOMapper.toResponse(pixKeyService.findBy(pixKeyDTO));
        return ResponseEntity.ok(userResponse);
    }

}
