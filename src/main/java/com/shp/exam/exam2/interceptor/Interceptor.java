package com.shp.exam.exam2.interceptor;

import com.shp.exam.exam2.container.ContainerComponent;
import com.shp.exam.exam2.http.Rq;

public abstract class Interceptor implements ContainerComponent {
	abstract public boolean runBeforeAction(Rq rq);
}
