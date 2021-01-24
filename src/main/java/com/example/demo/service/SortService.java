package com.example.demo.service;

import java.util.List;

/**
 * Api interface with method signature showing the contract for the sort
 * functionality.
 * 
 * @author amin
 *
 */
public interface SortService<T> {

	/**
	 * Sorts the list of objects.
	 * 
	 * @param list of objects to be sorted.
	 * 
	 * @return the sorted objects list
	 */
	List<T> sort(final List<T> objectList);

}
