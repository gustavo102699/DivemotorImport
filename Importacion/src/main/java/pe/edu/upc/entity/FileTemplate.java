package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FileTemplate")
public class FileTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTemplate;
	
	@Column(name = "TemplateName", nullable = false, length = 20)
	private String TemplateName;
	
	@Column(name = "request", nullable = false, length = 300)
	private String request;

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public void setTemplateName(String templateName) {
		TemplateName = templateName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public FileTemplate(Long idTemplate, String templateName, String request) {
		super();
		this.idTemplate = idTemplate;
		TemplateName = templateName;
		this.request = request;
	}

	
}
