package bg.enterprise.parent_app.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ExceptionHandlingUtils {

  public static Throwable getRootCause(Throwable e) {
    return e.getCause() != null ? getRootCause(e.getCause()) : e;
  }

  public static String getRootCauseMessage(Throwable e) {
    return getRootCause(e).getMessage();
  }
}