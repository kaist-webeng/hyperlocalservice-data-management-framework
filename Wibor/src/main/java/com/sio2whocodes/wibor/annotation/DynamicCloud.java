package com.sio2whocodes.wibor.annotation;

import com.sio2whocodes.wibor.util.QoSEum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface DynamicCloud {
    QoSEum QoS() default QoSEum.NETWORK_TRAFFIC;
}
