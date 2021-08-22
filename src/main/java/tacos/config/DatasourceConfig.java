package tacos.config;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class DatasourceConfig {

	final
	Environment env;

	public DatasourceConfig(Environment env) {
		this.env = env;
	}

	@Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public JdbcDataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(env.getProperty("spring.datasource.url"));
		dataSource.setUser(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));

//		Context ctx = new InitialContext();
//		ctx.bind("jdbc/dsName", dataSource);
//
//		Context ctx = new InitialContext();
//		DataSource ds = (DataSource) ctx.lookup("jdbc/dsName");
//		Connection conn = ds.getConnection();
		return dataSource;
	}

}
