package za.co.mmiholdings;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import za.co.mmiholdings.server.PersonEndpoint;

@EnableWs
@Configuration
@ComponentScan("za.co.mmiholdings")
public class SoapServerConfig extends WsConfigurerAdapter {

	/*@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(appContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/personws/*");
	}*/

	@Bean(name = "person")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PersonPort");
		wsdl11Definition.setLocationUri("/personws");
		wsdl11Definition.setTargetNamespace(PersonEndpoint.NAMESPACE_URI);
		wsdl11Definition.setSchema(schema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema beersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/person.xsd"));
	}

}
