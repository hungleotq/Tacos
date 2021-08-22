//package tacos.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//@Configuration
//@PropertySource({ "classpath:application.properties" })
//public class JdbcConfiguration {
//
//    @Autowired
//    private Environment env;
//    
//	@Bean
//	public DataSource getDataSource() {
//		DataSourceBuilder<D> dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName(env.getProperty("spring.h2.console.enabled"));
//		dataSourceBuilder.url(env.getProperty("jdbc:h2:tcp://localhost/~/test"));
//		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
//		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
//		return dataSourceBuilder.build();
//	}
//}
