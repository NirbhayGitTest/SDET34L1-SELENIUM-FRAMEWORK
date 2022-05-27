package com.sdet34l1.genericLibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * 
 * @author Nirbhay The Omen
 *
 */
public class RetryAnalyzerListenerImplementation implements IAnnotationTransformer{
	
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(com.sdet34l1.genericLibrary.RetryAnalyzerImplementation.class);
		
	}
	
}