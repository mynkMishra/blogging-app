import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
public class JPATestConfig {
	
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("jdbc:h2:mem:test");
		return dataSource;
	}
}
