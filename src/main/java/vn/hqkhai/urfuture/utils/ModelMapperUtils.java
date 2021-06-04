package vn.hqkhai.urfuture.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class ModelMapperUtils {

	public static <S, T> T mapObject(S source, Class<T> targetClass) {
		ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(source, targetClass);
	}
	
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		ModelMapper modelMapper = new ModelMapper();
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
	
}
