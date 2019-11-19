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
	
	public FileTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTemplate;
	
	@Column(name = "templateName", nullable = false, length = 50)
	private String templateName;
	
	@Column(name = "body", nullable = false,length = 800)
	private String body;

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName1) {
		templateName = templateName1;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String request) {
		this.body = request;
	}

	public FileTemplate(Long idTemplate, String templateName1, String request) {
		super();
		this.idTemplate = idTemplate;
		templateName = templateName1;
		this.body = request;
	}

	
}
