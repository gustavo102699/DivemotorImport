package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="LegalFiles")
public class LegalFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLegalFiles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTemplate")
	private FileTemplate idTemplate;
	
	@NotEmpty(message = "Redacte el archivo")
	@Column(name = "first_name", nullable = false, length = 700)
	private String bodyFile;
	
	@Column(name = "createDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Long getIdLegalFiles() {
		return idLegalFiles;
	}

	public void setIdLegalFiles(Long idLegalFiles) {
		this.idLegalFiles = idLegalFiles;
	}

	public FileTemplate getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(FileTemplate idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getBodyFile() {
		return bodyFile;
	}

	public void setBodyFile(String bodyFile) {
		this.bodyFile = bodyFile;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public LegalFiles(Long idLegalFiles, FileTemplate idTemplate,
			@NotEmpty(message = "Redacte el archivo") String bodyFile, Date createDate) {
		super();
		this.idLegalFiles = idLegalFiles;
		this.idTemplate = idTemplate;
		this.bodyFile = bodyFile;
		this.createDate = createDate;
	}
	
	
}
