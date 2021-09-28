package club.forhouse.services;

import club.forhouse.configuration.OperationMapper;
import club.forhouse.dto.OperationDto;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public Page<OperationDto> getAll(int page, int size) {
        return operationRepository.findAll(PageRequest.of(page, size)).map(operationMapper::toDto);
    }

    public OperationDto getById(long id) {
        return operationRepository.findById(id)
                .map(operationMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find Operation with id:" + id));
    }
}