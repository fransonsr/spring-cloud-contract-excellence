package com.example.videocat.api.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.videocat.api.data.Video;
import com.example.videocat.api.data.VideoRespository;

@RestController
public class VideoCatAPIController {

	private final DozerBeanMapper dozerBeanMapper;
	private final VideoRespository videoRepository;

	public VideoCatAPIController(DozerBeanMapper dozerBeanMapper, VideoRespository videoRepository) {
		this.dozerBeanMapper = dozerBeanMapper;
		this.videoRepository = videoRepository;
	}


	@GetMapping("/videos")
	public List<VideoRepresentation> list() {
		List<Video> list = videoRepository.findAll();
		return list.stream()
				.map(v -> dozerBeanMapper.map(v, VideoRepresentation.class))
				.collect(toList());
	}
}
