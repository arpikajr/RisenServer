package com.risen.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risen.dto.SubRisenDto;
import com.risen.exception.SpringRisenException;
import com.risen.mapper.SubRisenMapper;
import com.risen.model.SubRisen;
import com.risen.repository.SubRisenRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SubRisenService {

	private final SubRisenRepository subRisenRepository;
	private final SubRisenMapper subRisenMapper;

	@Transactional
	public SubRisenDto save(SubRisenDto subRisenDto) {
		log.debug("saving subRisen");
		SubRisen save = subRisenRepository.save(subRisenMapper.mapDtoToSubRisen(subRisenDto));
		subRisenDto.setId(save.getId());
		return subRisenDto;
	}

	@Transactional(readOnly = true)
	public List<SubRisenDto> getAll() {
		return subRisenRepository.findAll().stream().map(subRisenMapper::mapSubRisenToDto).collect(toList());
	}

	public SubRisenDto getSubRisen(Long id) {
		SubRisen subRisen = subRisenRepository.findById(id)
				.orElseThrow(() -> new SpringRisenException("No subRisen found with ID - " + id));
		return subRisenMapper.mapSubRisenToDto(subRisen);
	}
}