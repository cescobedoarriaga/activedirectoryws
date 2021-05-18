package mx.com.engen.activedirectoryws;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Web config.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  /**
   * Add formatters.
   *
   * @param registry the registry
   */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    // Do nothing because of X and Y.
  }
}
