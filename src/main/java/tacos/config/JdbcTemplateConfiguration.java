//package tacos.config;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnMissingBean(JdbcOperations.class)
//class JdbcTemplateConfiguration {
//
//    @Bean
//    @Primary
//    JdbcTemplate jdbcTemplate() {
//        InitialContext initialcontext;
//        JdbcProperties properties = new JdbcProperties();
//        JdbcTemplate jdbcTemplate = null;
//		try {
//			initialcontext = new InitialContext();
//			DataSource datasource = (DataSource)initialcontext.lookup("");
//			jdbcTemplate = new JdbcTemplate(datasource);
//			JdbcProperties.Template template = properties.getTemplate();
//			jdbcTemplate.setFetchSize(template.getFetchSize());
//			jdbcTemplate.setMaxRows(template.getMaxRows());
//			if (template.getQueryTimeout() != null) {
//				jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
//			}
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return jdbcTemplate;
//    }
//
//}