package com.vbv.corona_desinfector;

public interface Config  {

	<T> Class<? extends T> getImplClass(Class<T> ifc);

}
