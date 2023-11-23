package com.sio2whocodes.wibor.annotation;

import com.sio2whocodes.wibor.databaseRegistry.RegistryDto;
import com.sio2whocodes.wibor.databaseRegistry.RegistryService;
import com.sio2whocodes.wibor.routingDB.DataSourceConfig;
import com.sio2whocodes.wibor.routingDB.LocationContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

@Component
@Aspect
public class RoutingAspect<T> {
    @Autowired
    private RegistryService registryService;
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Before("execution(* com.sio2whocodes.wibor.repository..*.*(..))")
    public void beforeRepository(JoinPoint joinPoint) throws Exception {
        LocationContextHolder.clearLocationContext();
        if (Arrays.stream(joinPoint.getThis().getClass().getInterfaces()).findFirst().isPresent()) {
            Class givenInterface = Arrays.stream(joinPoint.getThis().getClass().getInterfaces())
                .findFirst().get();
            Type genericClazz = Arrays.stream(givenInterface.getGenericInterfaces()).findFirst()
                .get();
            ParameterizedType pt;
            if (LocationContextHolder.getLocationContext() == null) {
                if (genericClazz instanceof ParameterizedType) {
                    pt = (ParameterizedType) Arrays.stream(givenInterface.getGenericInterfaces())
                        .findFirst().get();
                    Class entity = (Class) Arrays.stream(pt.getActualTypeArguments()).findFirst()
                        .get();

                    if (Arrays.stream(entity.getAnnotations())
                        .anyMatch(a -> a instanceof DynamicFog || a instanceof Dynamic || a instanceof DynamicCloud)) {
                        RegistryDto databaseInfo = registryService.getDatabase(
                            entity.getSimpleName().toLowerCase(), "A");
                        dataSourceConfig.setCurrentTenant(databaseInfo);
                    } else if (Arrays.stream(entity.getAnnotations()).anyMatch(a -> a instanceof StaticCloud)) {
                        LocationContextHolder.setLocationContext("cloud");
                    } else if (Arrays.stream(entity.getAnnotations()).anyMatch(a -> a instanceof StaticFog)) {
                        LocationContextHolder.setLocationContext("local");
                    }
                }
            }
        }
    }
}
