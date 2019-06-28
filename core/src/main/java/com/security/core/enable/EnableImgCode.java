package com.security.core.enable;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import com.security.core.autoconfig.ValidateCodeConfig;

@Retention(RUNTIME)
@Target(TYPE)
@Import(ValidateCodeConfig.class)
public @interface EnableImgCode {

}
